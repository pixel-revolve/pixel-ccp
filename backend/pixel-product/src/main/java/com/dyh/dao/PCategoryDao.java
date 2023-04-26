package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品类别(PCategory)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-23 15:21:34
 */
@Mapper
public interface PCategoryDao extends BaseMapper<PCategory> {

}

