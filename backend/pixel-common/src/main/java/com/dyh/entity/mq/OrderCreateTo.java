package com.dyh.entity.mq;

import com.dyh.entity.po.POrder;
import com.dyh.entity.po.POrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单创建
 *
 * @author pixel-revolve
 * @date 2023/04/10
 */
@Data
public class OrderCreateTo {

    private POrder order;

    private List<POrderItem> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;

}
