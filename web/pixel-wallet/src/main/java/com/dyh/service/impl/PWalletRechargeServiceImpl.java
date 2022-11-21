package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PWalletRechargeDao;
import com.dyh.entity.PWalletRecharge;
import com.dyh.service.PWalletRechargeService;
import org.springframework.stereotype.Service;

/**
 * 钱包流水(PWalletRecharge)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 13:24:40
 */
@Service("pWalletRechargeService")
public class PWalletRechargeServiceImpl extends ServiceImpl<PWalletRechargeDao, PWalletRecharge> implements PWalletRechargeService {

}

