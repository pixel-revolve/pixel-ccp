package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PAttrGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品属性组(PAttrGroup)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-23 17:07:41
 */
@Mapper
public interface PAttrGroupDao extends BaseMapper<PAttrGroup> {

}

