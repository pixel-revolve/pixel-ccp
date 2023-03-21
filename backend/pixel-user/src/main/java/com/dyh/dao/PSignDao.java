package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PSign;
import org.apache.ibatis.annotations.Mapper;

/**
 * (PSign)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 14:10:42
 */
@Mapper
public interface PSignDao extends BaseMapper<PSign> {

}

