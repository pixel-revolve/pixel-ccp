package com.dyh.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PPostVo {
    //主题ID
    private Long id;
    //用户ID
    private Long userId;
    //用户昵称
    private String nickname;
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
}
