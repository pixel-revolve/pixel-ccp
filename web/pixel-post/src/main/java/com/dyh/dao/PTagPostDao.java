package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PTagPost;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签文章关联表(PTagPost)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-26 10:30:30
 */
@Mapper
public interface PTagPostDao extends BaseMapper<PTagPost> {

}

