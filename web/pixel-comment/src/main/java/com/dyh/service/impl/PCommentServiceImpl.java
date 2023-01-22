package com.dyh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PCommentDao;
import com.dyh.entity.PComment;
import com.dyh.entity.PCommentContent;
import com.dyh.entity.PCommentReply;
import com.dyh.entity.dto.PCommentContentDTO;
import com.dyh.entity.dto.PCommentReplyDTO;
import com.dyh.entity.dto.PPostContentDTO;
import com.dyh.entity.po.PUser;
import com.dyh.entity.vo.PCommentDisplayVo;
import com.dyh.entity.vo.PCommentPostVo;
import com.dyh.feign.PUserFeignService;
import com.dyh.service.PCommentContentService;
import com.dyh.service.PCommentReplyService;
import com.dyh.service.PCommentService;
import com.dyh.utils.RedisIdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dyh.constant.CommentConstants.COMMENT_CONTENT_PREFIX;
import static com.dyh.constant.CommentConstants.COMMENT_PREFIX;

/**
 * 评论(PComment)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 13:33:01
 */
@Service("pCommentService")
public class PCommentServiceImpl extends ServiceImpl<PCommentDao, PComment> implements PCommentService {

    @Resource
    RedisIdWorker redisIdWorker;

    @Resource
    PCommentContentService pCommentContentService;

    @Resource
    PCommentReplyService pCommentReplyService;

    @Resource
    PUserFeignService pUserFeignService;

    @Resource
    ObjectMapper objectMapper;

    @Override
    public R postComment(PCommentPostVo pCommentPostVo) {
        // 1.为创建的评论生成分布式ID
        long commentId = redisIdWorker.nextId(COMMENT_PREFIX);
        // 2.将vo中需要的内容取出
        List<PCommentContentDTO> pCommentContentDTOS = pCommentPostVo.getContents();

        // 获取评论的文章和发布该评论的用户
        Long postId = pCommentPostVo.getPostId();
        Long userId = pCommentPostVo.getUserId();

        // 3.将评论内容存储到数据库
        pCommentContentDTOS.forEach((i)->{
            PCommentContent pCommentContent = BeanUtil.copyProperties(i, PCommentContent.class);
            pCommentContent.setCommentId(commentId);
            pCommentContent.setUserId(userId);
            pCommentContent.setId(redisIdWorker.nextId(COMMENT_CONTENT_PREFIX));
            // 保存评论内容到数据库
            pCommentContentService.save(pCommentContent);
        });

        PComment pComment=new PComment();
        pComment.setId(commentId);
        pComment.setUserId(userId);
        pComment.setPostId(postId);
        pComment.setIp(pCommentPostVo.getIp());
        // 5.保存评论信息
        save(pComment);

        return R.ok(commentId);
    }

    @Override
    public R displayComment(Long postId) {
        // 1.首先根据 postId 查询出来文章下的所有评论
        List<PComment> getComments = this.baseMapper.selectList(new QueryWrapper<PComment>().eq("post_id", postId));
        if (CollectionUtils.isEmpty(getComments)){
            return R.failed("评论为空");
        }
        // 2.将评论下的所有回复和内容查出并组装
        List<PCommentDisplayVo> res = getComments.stream().map(comment -> {
            Long commentId = comment.getId();
            Long commentUserId = comment.getUserId();
            PUser getUser=null;
            // 2.0.获取发评论的用户的信息
            try {
                getUser = objectMapper.readValue(objectMapper.writeValueAsString(pUserFeignService.selectOne(commentUserId).getData()), PUser.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 2.1.获得当前评论下的所有回复
            List<PCommentReplyDTO> replies = pCommentReplyService.getBaseMapper().selectList(new QueryWrapper<PCommentReply>().eq("comment_id", commentId))
                    .stream().map(reply -> {
                        Long userId = reply.getUserId();
                        Long atUserId = reply.getAtUserId();
                        String nickname = pUserFeignService.selectNicknameById(userId).getData().toString();
                        String atNickname = pUserFeignService.selectNicknameById(atUserId).getData().toString();
                        PCommentReplyDTO pCommentReplyDTO = BeanUtil.copyProperties(reply, PCommentReplyDTO.class);
                        pCommentReplyDTO.setNickname(nickname);
                        pCommentReplyDTO.setAtNickname(atNickname);
                        return pCommentReplyDTO;
                    }).collect(Collectors.toList());
            // 2.2.获得当前评论的所有内容
            List<PCommentContentDTO> contents = pCommentContentService.getByCommentId(commentId).getData()
                    .stream().map(content -> BeanUtil.copyProperties(content, PCommentContentDTO.class))
                    .collect(Collectors.toList());
            PCommentDisplayVo pCommentDisplayVo = BeanUtil.copyProperties(comment, PCommentDisplayVo.class);
            // 2.3.组装并且返回
            pCommentDisplayVo.setContents(contents);
            pCommentDisplayVo.setReplies(replies);
            assert getUser != null;
            pCommentDisplayVo.setNickname(getUser.getNickname());
            pCommentDisplayVo.setAvatar(getUser.getAvatar());
            return pCommentDisplayVo;
        }).collect(Collectors.toList());
        return R.ok(res);
    }
}

