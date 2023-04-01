package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.POrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单(POrder)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 19:05:23
 */
@Mapper
public interface POrderDao extends BaseMapper<POrder> {

}

