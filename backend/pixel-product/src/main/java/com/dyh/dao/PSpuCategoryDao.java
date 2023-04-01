package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PSpuCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品类别(PSpuCategory)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 18:58:08
 */
@Mapper
public interface PSpuCategoryDao extends BaseMapper<PSpuCategory> {

}

