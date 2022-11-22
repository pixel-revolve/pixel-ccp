package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PPostContent;
import org.apache.ibatis.annotations.Mapper;

/**
 * 冒泡/文章内容(PPostContent)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 12:37:18
 */
@Mapper
public interface PPostContentDao extends BaseMapper<PPostContent> {

}

