package com.dyh.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PCommentReplyDTO {
    //评论ID
    private Long commentId;
    //用户ID
    private Long userId;
    //用户昵称
    private String nickname;
    //@用户ID
    private Long atUserId;
    //@用户昵称
    private String atNickname;
    //内容
    private String content;
    //IP地址
    private String ip;
    //IP城市地址
    private String ipLoc;
    //创建时间
    private Date createdOn;
}
