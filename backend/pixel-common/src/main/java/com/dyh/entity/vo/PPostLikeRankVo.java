package com.dyh.entity.vo;

import lombok.Data;

@Data
public class PPostLikeRankVo {
    // 文章id
    private Long id;
    // 点赞数
    private Long upvoteCount;
    // 排名
    private Long sequence;
    // 标题
    private String title;
}
