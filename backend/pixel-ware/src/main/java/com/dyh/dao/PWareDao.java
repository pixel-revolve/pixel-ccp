package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PWare;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库(PWare)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@Mapper
public interface PWareDao extends BaseMapper<PWare> {

}

