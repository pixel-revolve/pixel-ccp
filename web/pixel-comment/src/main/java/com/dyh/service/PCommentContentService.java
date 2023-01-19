package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PCommentContent;

import java.util.List;

/**
 * 评论内容(PCommentContent)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 13:33:14
 */
public interface PCommentContentService extends IService<PCommentContent> {
    R<List<PCommentContent>> getByCommentId(Long commentId);

}

