package com.dyh.entity.vo;

import com.dyh.entity.dto.PCommentContentDTO;
import lombok.Data;

import java.util.List;

@Data
public class PCommentPostVo {
    //POST ID
    private Long postId;
    //用户ID
    private Long userId;
    //IP地址
    private String ip;
    // 评论内容
    private List<PCommentContentDTO> contents;
}
