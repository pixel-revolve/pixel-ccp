package com.dyh.entity.vo;

import com.dyh.entity.dto.PPostContentDTO;
import lombok.Data;

import java.util.List;

/**
 * 创作文章锁需要的数据
 *
 * @author pixel-revolve
 * @date 2022/11/25
 */
@Data
public class PPostCreateVo {
    //用户昵称
    private String nickname;
    //摘要
    private String summary;
    // 文章内容
    private List<PPostContentDTO> contents;
    //标签
    private List<String> tags;
    //附件价格(分)
    private Long attachmentPrice;
}
