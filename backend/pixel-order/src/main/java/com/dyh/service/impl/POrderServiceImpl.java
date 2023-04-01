package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.POrderDao;
import com.dyh.entity.POrder;
import com.dyh.service.POrderService;
import org.springframework.stereotype.Service;

/**
 * 订单(POrder)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 19:05:23
 */
@Service("pOrderService")
public class POrderServiceImpl extends ServiceImpl<POrderDao, POrder> implements POrderService {

}

