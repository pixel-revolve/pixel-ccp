package com.dyh.entity.vo;

import lombok.Data;

@Data
public class PUserMeVo {
    //用户ID
    private Long id;
    //昵称
    private String nickname;
    //用户名
    private String username;
    //手机号
    private String phone;
    //邮箱
    private String email;
    //状态，1正常，2停用
    private Integer status;
    //用户头像
    private String avatar;
    //用户余额（分）
    private Long balance;
    //是否管理员
    private Integer isAdmin;
}
