package com.dyh.service.impl;

import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.constants.OrderStatusEnum;
import com.dyh.dao.PWalletRechargeDao;
import com.dyh.entity.PWalletRecharge;
import com.dyh.service.PWalletRechargeService;
import org.springframework.stereotype.Service;

/**
 * 钱包流水(PWalletRecharge)表服务实现类
 *
 * @author makejava
 * @since 2023-03-18 21:33:27
 */
@Service("pWalletRechargeService")
public class PWalletRechargeServiceImpl extends ServiceImpl<PWalletRechargeDao, PWalletRecharge> implements PWalletRechargeService {

    @Override
    public void updateOrderStatus(String orderId, String alpayFlowNum, String paidAmount) {
        PWalletRecharge pWalletRecharge=getById(orderId);
        if (pWalletRecharge.getTradeStatus().equals(OrderStatusEnum.WAIT_PAY.key)) {
            pWalletRecharge.setTradeStatus(OrderStatusEnum.PAID.key);
            this.baseMapper.updateById(pWalletRecharge);
        }
    }
}

