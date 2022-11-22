package com.dyh.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * 冒泡/文章(PPost)表实体类
 *
 * @author makejava
 * @since 2022-11-20 12:35:24
 */
@SuppressWarnings("serial")
public class PPost extends Model<PPost> {
    //主题ID
    private Long id;
    //用户ID
    private String userId;
    //评论数
    private String commentCount;
    //收藏数
    private String collectionCount;
    //点赞数
    private String upvoteCount;
    //可见性 0公开 1私密 2好友可见
    private String visibility;
    //是否置顶
    private String isTop;
    //是否精华
    private String isEssence;
    //是否锁定
    private String isLock;
    //最新回复时间
    private String latestRepliedOn;
    //标签
    private String tags;
    //附件价格(分)
    private String attachmentPrice;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(String collectionCount) {
        this.collectionCount = collectionCount;
    }

    public String getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(String upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsEssence() {
        return isEssence;
    }

    public void setIsEssence(String isEssence) {
        this.isEssence = isEssence;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public String getLatestRepliedOn() {
        return latestRepliedOn;
    }

    public void setLatestRepliedOn(String latestRepliedOn) {
        this.latestRepliedOn = latestRepliedOn;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAttachmentPrice() {
        return attachmentPrice;
    }

    public void setAttachmentPrice(String attachmentPrice) {
        this.attachmentPrice = attachmentPrice;
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

