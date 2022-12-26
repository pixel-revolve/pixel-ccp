package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PCommentDao;
import com.dyh.entity.PComment;
import com.dyh.service.PCommentService;
import org.springframework.stereotype.Service;

/**
 * 评论(PComment)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 13:33:01
 */
@Service("pCommentService")
public class PCommentServiceImpl extends ServiceImpl<PCommentDao, PComment> implements PCommentService {

    @Override
    public R sendComment(Long postId) {

        return null;
    }
}

