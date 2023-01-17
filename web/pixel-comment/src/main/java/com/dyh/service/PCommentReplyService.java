package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PCommentReply;
import com.dyh.entity.vo.PCommentReplyVo;

/**
 * 评论回复(PCommentReply)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 13:33:24
 */
public interface PCommentReplyService extends IService<PCommentReply> {
    R replyComment(PCommentReplyVo pCommentReplyVo);
}

