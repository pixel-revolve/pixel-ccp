package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PSku;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品库存表(PSku)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-23 14:26:54
 */
@Mapper
public interface PSkuDao extends BaseMapper<PSku> {

}

