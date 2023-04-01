package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PSpu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品表(PSpu)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 18:54:53
 */
@Mapper
public interface PSpuDao extends BaseMapper<PSpu> {

}

