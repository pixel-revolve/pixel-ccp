package com.dyh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PCommentReplyDao;
import com.dyh.entity.PCommentReply;
import com.dyh.entity.vo.PCommentReplyVo;
import com.dyh.service.PCommentReplyService;
import com.dyh.utils.RedisIdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.dyh.constant.CommentConstants.COMMENT_REPLY_PREFIX;

/**
 * 评论回复(PCommentReply)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 13:33:24
 */
@Service("pCommentReplyService")
public class PCommentReplyServiceImpl extends ServiceImpl<PCommentReplyDao, PCommentReply> implements PCommentReplyService {

    @Resource
    RedisIdWorker redisIdWorker;

    @Override
    public R replyComment(PCommentReplyVo pCommentReplyVo) {
        // 1.为评论回复生成分布式id
        long commentReplyId = redisIdWorker.nextId(COMMENT_REPLY_PREFIX);
        PCommentReply pCommentReply = BeanUtil.copyProperties(pCommentReplyVo, PCommentReply.class);
        pCommentReply.setId(commentReplyId);
        save(pCommentReply);
        return R.ok(commentReplyId);
    }
}

