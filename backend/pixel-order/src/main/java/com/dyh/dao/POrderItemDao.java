package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.POrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品(POrderItem)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 19:05:24
 */
@Mapper
public interface POrderItemDao extends BaseMapper<POrderItem> {

}

