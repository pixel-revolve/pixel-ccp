package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PSpuAttrValue;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品属性值(PSpuAttrValue)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-23 17:17:15
 */
@Mapper
public interface PSpuAttrValueDao extends BaseMapper<PSpuAttrValue> {

}

