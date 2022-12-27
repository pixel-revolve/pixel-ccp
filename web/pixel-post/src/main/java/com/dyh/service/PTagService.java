package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PTag;

/**
 * 标签(PTag)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 12:36:57
 */
public interface PTagService extends IService<PTag> {
    R getByTag(String tag);
}

