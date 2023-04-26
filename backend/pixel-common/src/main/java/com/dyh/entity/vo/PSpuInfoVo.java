package com.dyh.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PSpuInfoVo {
    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String spuName;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品单价
     */
    private Long price;
    /**
     * 所属分类id
     */
    private Long catalogId;
    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    private Integer publishStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
