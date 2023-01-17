package com.dyh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PCommentDao;
import com.dyh.entity.PComment;
import com.dyh.entity.PCommentContent;
import com.dyh.entity.dto.PCommentContentDTO;
import com.dyh.entity.vo.PCommentPostVo;
import com.dyh.service.PCommentContentService;
import com.dyh.service.PCommentService;
import com.dyh.utils.RedisIdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.dyh.constant.CommentConstants.*;

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
}

