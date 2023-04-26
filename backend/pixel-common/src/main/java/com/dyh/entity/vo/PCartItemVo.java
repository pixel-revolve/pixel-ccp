package com.dyh.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 购物项内容
 * @author pixel-revolve
 * @date 2023/04/15
 */
@Data
public class PCartItemVo {

    //商品skuID
    private Long skuId;
    //商品展示图
    private String pic;
    //商品购买数量
    private Integer count;
    //商品价格（分）
    private Long price;
    //商品总价格（分）
    private Long totalPrice;
    //商品套餐属性
    private List<String> skuAttrValues;
    //是否选中
    private Boolean check=true;

}
