package com.dyh.entity.vo;

import lombok.Data;

import java.math.BigDecimal;


/**
 * 封装订单提交数据的vo
 *
 * @author pixel-revolve
 * @date 2023/04/10
 */
@Data
public class POrderSubmitVo {

    // 收获地址的id
    private Long addrId;

    // 支付方式
    private Integer payType;
    //无需提交要购买的商品，去购物车再获取一遍
    //优惠、发票

    // 防重令牌
    private String orderToken;

    // 应付价格
    private Long payPrice;

    // 订单备注
    private String remarks;
}
