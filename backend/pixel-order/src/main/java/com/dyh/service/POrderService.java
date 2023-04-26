package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.POrder;
import com.dyh.entity.vo.POrderSubmitVo;
import com.dyh.entity.vo.POrderConfirmVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

/**
 * 订单(POrder)表服务接口
 *
 * @author makejava
 * @since 2023-04-01 19:05:23
 */
public interface POrderService extends IService<POrder> {
    /**
     * 关闭订单
     * @param order
     */
    void closeOrder(POrder order);

    POrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    /**
     * 提交订单
     * @param vo
     * @return {@link R}
     * @throws JsonProcessingException
     */
    @Transactional(rollbackFor = Exception.class)
    R submitOrder(POrderSubmitVo vo) throws JsonProcessingException;

}

