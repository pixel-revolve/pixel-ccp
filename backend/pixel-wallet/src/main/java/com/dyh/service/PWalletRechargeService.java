package com.dyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PWalletRecharge;

/**
 * 钱包流水(PWalletRecharge)表服务接口
 *
 * @author makejava
 * @since 2023-03-18 21:33:27
 */
public interface PWalletRechargeService extends IService<PWalletRecharge> {

    void updateOrderStatus(String orderId, String alpayFlowNum, String paidAmount);

}

