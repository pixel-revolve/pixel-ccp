package com.dyh.entity.dto;

import lombok.Data;

@Data
public class PPostContentDTO {
    // 文章内容
    String content;
    // 内容类型
    int type;
    // 内容排序
    int sort;
}
