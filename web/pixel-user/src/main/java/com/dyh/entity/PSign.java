package com.dyh.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (PSign)表实体类
 *
 * @author makejava
 * @since 2022-11-23 14:10:42
 */
@SuppressWarnings("serial")
public class PSign extends Model<PSign> {
    //主键
    private Long id;
    //用户id
    private Long userId;
    //最新签到日期
    private String date;
    //连续签到的天数
    private Long count;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

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

