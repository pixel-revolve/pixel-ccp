package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签(PTag)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 12:36:57
 */
@Mapper
public interface PTagDao extends BaseMapper<PTag> {

}

