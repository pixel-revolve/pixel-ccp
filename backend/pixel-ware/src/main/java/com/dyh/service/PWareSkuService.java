package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PWareSku;
import com.dyh.entity.mq.OrderTo;
import com.dyh.entity.mq.StockLockedTo;
import com.dyh.entity.vo.PSkuHasStockVo;
import com.dyh.entity.vo.PWareSkuLockVo;

import java.util.List;

/**
 * 产品库存(PWareSku)表服务接口
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
public interface PWareSkuService extends IService<PWareSku> {
    /**
     * 解锁库存
     * @param to
     */
    void unlockStock(StockLockedTo to);

    /**
     * 解锁订单
     * @param orderTo
     */
    void unlockStock(OrderTo orderTo);


    /**
     * 锁定库存
     * @param vo
     * @return
     */
    R orderLockStock(PWareSkuLockVo vo);

    /**
     * 判断是否有库存
     *
     * @param skuIds
     * @return {@link R}
     */
    R<List<PSkuHasStockVo>> getSkuHasStock(List<Long> skuIds);
}

