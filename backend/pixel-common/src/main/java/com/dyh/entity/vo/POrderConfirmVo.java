package com.dyh.entity.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * 订单确认页需要用的数据
 * @author: pixel-revolve
 * @date: 2023/04/23
 */
@Data
public class POrderConfirmVo {
    // 用户收获地址列表
    List<PUserAddressVo> userAddressVos;

    // 所有选中的购物项
    List<POrderItemVo> items;

    // 防止重复提交的令牌
    private String orderToken;

    // 库存
    Map<Long,Boolean> stocks;
}
