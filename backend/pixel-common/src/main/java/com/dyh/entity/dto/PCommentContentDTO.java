package com.dyh.entity.dto;

import lombok.Data;

@Data
public class PCommentContentDTO {
    //评论ID
    private Long commentId;
    //用户ID
    private Long userId;
    //内容
    private String content;
    //类型，1标题，2文字段落，3图片地址，4视频地址，5语音地址，6链接地址
    private int type;
    //排序，越小越靠前
    private Long sort;
}
