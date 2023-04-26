package com.dyh.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 锁定库存Vo
 *
 * @author pixel-revolve
 * @date 2023/04/17
 */
@Data
public class PWareSkuLockVo {

    // 订单号
    private Long orderId;

    // 需要锁住的所有库存信息
    private List<POrderItemVo> locks;
}
