package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PCommentContent;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论内容(PCommentContent)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 13:33:14
 */
@Mapper
public interface PCommentContentDao extends BaseMapper<PCommentContent> {

}

