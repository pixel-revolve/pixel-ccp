package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PAttr;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品属性(PAttr)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-23 17:05:57
 */
@Mapper
public interface PAttrDao extends BaseMapper<PAttr> {

}

