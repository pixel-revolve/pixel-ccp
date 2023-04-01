package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PWareSku;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品库存(PWareSku)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@Mapper
public interface PWareSkuDao extends BaseMapper<PWareSku> {

}

