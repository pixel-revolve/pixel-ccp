package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PCommentReplyDao;
import com.dyh.entity.PCommentReply;
import com.dyh.service.PCommentReplyService;
import org.springframework.stereotype.Service;

/**
 * 评论回复(PCommentReply)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 13:33:24
 */
@Service("pCommentReplyService")
public class PCommentReplyServiceImpl extends ServiceImpl<PCommentReplyDao, PCommentReply> implements PCommentReplyService {

}

