package com.dyh.entity.vo;

import com.alibaba.nacos.client.logging.logback.LogbackNacosLogging;
import com.dyh.entity.dto.PCommentContentDTO;
import com.dyh.entity.dto.PCommentReplyDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PCommentDisplayVo {
    //主键ID
    private Long id;
    //POST ID
    private Long postId;
    //用户ID
    private Long userId;
    //昵称
    private String nickname;
    //头像
    private String avatar;
    //IP地址
    private String ip;
    //IP城市地址
    private String ipLoc;
    //创建时间
    private Date createdOn;
    //评论内容
    private List<PCommentContentDTO> contents;
    //评论回复
    private List<PCommentReplyDTO> replies;
}
