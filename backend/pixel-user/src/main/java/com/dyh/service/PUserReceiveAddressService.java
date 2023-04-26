package com.dyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PUserReceiveAddress;

import java.util.List;

/**
 * 用户收货地址表
(PUserReceiveAddress)表服务接口
 *
 * @author makejava
 * @since 2023-04-11 22:19:57
 */
public interface PUserReceiveAddressService extends IService<PUserReceiveAddress> {

    List<PUserReceiveAddress> getAddress(Long userId);
}

