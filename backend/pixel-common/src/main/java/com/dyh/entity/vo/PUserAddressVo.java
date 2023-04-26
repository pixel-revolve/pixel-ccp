package com.dyh.entity.vo;

import lombok.Data;

@Data
public class PUserAddressVo {
    //主键
    private Long id;
    //用户ID
    private Long userId;
    //收货人姓名
    private String name;
    //电话
    private String phone;
    //城市
    private String city;
    //省份
    private String province;
    //详细地址（街道）
    private String detailAddress;
    //区域编码
    private String areacode;
    //是否默认
    private Integer defaultStatus;
}
