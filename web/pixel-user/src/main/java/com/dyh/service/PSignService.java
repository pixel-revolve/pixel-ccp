package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PSign;

/**
 * (PSign)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 14:10:42
 */
public interface PSignService extends IService<PSign> {

    R sign();

    R signWithMySQL();

    R signMonthCount();

    R signContinueCount();
}

