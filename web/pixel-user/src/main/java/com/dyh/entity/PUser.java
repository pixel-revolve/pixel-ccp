package com.dyh.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户(PUser)表实体类
 *
 * @author makejava
 * @since 2022-11-17 15:09:52
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class PUser extends Model<PUser> {
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
    //MD5密码
    private String password;
    //盐值
    private String salt;
    //状态，1正常，2停用
    private Integer status;
    //用户头像
    private String avatar;
    //用户余额（分）
    private Long balance;
    //是否管理员
    private Integer isAdmin;
    //创建时间
    private Date createdOn;
    //修改时间
    private Date modifiedOn;
    //删除时间
    private Date deletedOn;
    //是否删除 0 为未删除、1 为已删除
    @TableLogic
    private Integer isDel;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

