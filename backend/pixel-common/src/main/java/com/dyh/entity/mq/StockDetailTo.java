package com.dyh.entity.mq;

import lombok.Data;

/**
 * 库存细节
 * @author: pixel-revolve
 * @date: 2023/04/17
 */
@Data
public class StockDetailTo {

    /**
     * orderDetailTaskId
     */
    private Long id;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * sku_name
     */
    private String skuName;
    /**
     * 购买个数
     */
    private Integer skuNum;
    /**
     * 购买个数
     */
    private Integer quantity;
    /**
     * 工作单id
     */
    private Long taskId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 锁定状态
     */
    private Integer lockStatus;
}
