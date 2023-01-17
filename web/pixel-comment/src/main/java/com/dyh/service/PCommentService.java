package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PComment;
import com.dyh.entity.vo.PCommentPostVo;

/**
 * 评论(PComment)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 13:33:01
 */
public interface PCommentService extends IService<PComment> {

    R postComment(PCommentPostVo pCommentPostVo);
}

