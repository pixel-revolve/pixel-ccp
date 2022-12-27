package com.dyh.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * 评论回复(PCommentReply)表实体类
 *
 * @author makejava
 * @since 2022-11-20 13:33:24
 */
@SuppressWarnings("serial")
public class PCommentReply extends Model<PCommentReply> {
    //回复ID
    private Long id;
    //评论ID
    private Long commentId;
    //用户ID
    private Long userId;
    //@用户ID
    private Long atUserId;
    //内容
    private String content;
    //IP地址
    private String ip;
    //IP城市地址
    private String ipLoc;
    //创建时间
    private Date createdOn;
    //修改时间
    private Date modifiedOn;
    //删除时间
    private Date deletedOn;
    //是否删除 0 为未删除、1 为已删除
    private String isDel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAtUserId() {
        return atUserId;
    }

    public void setAtUserId(Long atUserId) {
        this.atUserId = atUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpLoc() {
        return ipLoc;
    }

    public void setIpLoc(String ipLoc) {
        this.ipLoc = ipLoc;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
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

