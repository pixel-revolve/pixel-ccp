package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PPostCollection;

/**
 * 冒泡/文章收藏(PPostCollection)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 12:36:42
 */
public interface PPostCollectionService extends IService<PPostCollection> {

    R collectPost(Long id);

    R selectByUserIdAndPostId(Long userId,Long postId);

    long countCollectionNumberByPostId(Long postId);
}

