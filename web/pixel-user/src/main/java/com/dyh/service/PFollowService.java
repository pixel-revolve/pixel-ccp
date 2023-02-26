package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PFollow;

/**
 * 关注(PFollow)表服务接口
 *
 * @author makejava
 * @since 2023-02-25 22:40:17
 */
public interface PFollowService extends IService<PFollow> {

    R follow(Long followUserId, Boolean isFollow);

    R isFollow(Long followUserId);

    R followCommons(Long id);
}

