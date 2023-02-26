package com.dyh.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 冒泡/文章(PPost)表实体类
 *
 * @author makejava
 * @since 2022-11-24 14:07:03
 */
@SuppressWarnings("serial")
public class PPost extends Model<PPost> {
    //主题ID
    private Long id;
    //用户ID
    private Long userId;
    //评论数
    private Long commentCount;
    //收藏数
    private Long collectionCount;
    //点赞数
    private Long upvoteCount;
    //查看数
    private Long watchCount;
    //摘要
    private String summary;
    //可见性 0公开 1私密 2好友可见
    private Integer visibility;
    //是否置顶
    private Integer isTop;
    //是否精华
    private Integer isEssence;
    //是否锁定
    private Integer isLock;
    //最新回复时间
    private Long latestRepliedOn;
    //标签
    private String tags;
    //附件价格(分)
    private Long attachmentPrice;
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
    private Integer isDel;


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

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Long collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Long getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(Long upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public Long getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Long watchCount) {
        this.watchCount = watchCount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsEssence() {
        return isEssence;
    }

    public void setIsEssence(Integer isEssence) {
        this.isEssence = isEssence;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Long getLatestRepliedOn() {
        return latestRepliedOn;
    }

    public void setLatestRepliedOn(Long latestRepliedOn) {
        this.latestRepliedOn = latestRepliedOn;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getAttachmentPrice() {
        return attachmentPrice;
    }

    public void setAttachmentPrice(Long attachmentPrice) {
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
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

