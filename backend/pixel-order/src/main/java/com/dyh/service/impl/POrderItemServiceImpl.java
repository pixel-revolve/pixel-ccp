package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.POrderItemDao;
import com.dyh.entity.POrderItem;
import com.dyh.service.POrderItemService;
import org.springframework.stereotype.Service;

/**
 * 订单商品(POrderItem)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 19:05:24
 */
@Service("pOrderItemService")
public class POrderItemServiceImpl extends ServiceImpl<POrderItemDao, POrderItem> implements POrderItemService {

}

