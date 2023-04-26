package com.dyh.entity.mq;

import lombok.Data;

import java.util.Date;

@Data
public class OrderTo {
    //订单ID
    private Long id;
    //用户ID
    private Long userId;
    //订单总金额
    private Long totalAmount;
    //应付金额（分）
    private Long payAmount;
    //支付方式：1->像素币；2->支付宝；3->微信； 4->货到付款；
    private Integer payType;
    //订单状态：0->待付款；1->已付款；2->已发货；3->已完成；4->已关闭；5->售后中；6->售后完成
    private Integer orderStatus;
    //收货人姓名
    private String receiverName;
    //收货人电话
    private String receiverPhone;
    //省份
    private String receiverProvince;
    //市
    private String receiverCity;
    //详细地址
    private String receiverDetailAddress;
    //订单备注
    private String note;
    //发货时间
    private Date deliveryTime;
    //确认收货时间
    private Date receiveTime;
    //创建时间
    private Date createdOn;
}
