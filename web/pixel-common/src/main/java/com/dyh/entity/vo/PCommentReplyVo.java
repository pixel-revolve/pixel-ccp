package com.dyh.entity.vo;

import lombok.Data;

@Data
public class PCommentReplyVo {
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
}
