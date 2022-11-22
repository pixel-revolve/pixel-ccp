package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PPost;
import org.apache.ibatis.annotations.Mapper;

/**
 * 冒泡/文章(PPost)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 12:35:24
 */
@Mapper
public interface PPostDao extends BaseMapper<PPost> {

}

