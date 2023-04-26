package com.dyh.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class POrderItemVo {
    //订单商品ID
    private Long id;
    //商品ID
    private Long skuId;
    //商品套餐属性
    private List<String> skuAttrValues;
    //商品展示图
    private String pic;
    //商品购买数量
    private Integer count;
    //商品价格（分）
    private Long price;
    //商品总价格（分）
    private Long totalPrice;
    //标题
    private String title;
    //是否选中
    private Boolean check;
}
