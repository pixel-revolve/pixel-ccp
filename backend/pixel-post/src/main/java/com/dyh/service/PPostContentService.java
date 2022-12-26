package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PPostContent;

import java.util.List;

/**
 * 冒泡/文章内容(PPostContent)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 12:37:18
 */
public interface PPostContentService extends IService<PPostContent> {
    R<List<PPostContent>> getByPostId(Long postId);
}

