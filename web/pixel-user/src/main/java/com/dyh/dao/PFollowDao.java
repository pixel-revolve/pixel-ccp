package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PFollow;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关注(PFollow)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-25 22:40:17
 */
@Mapper
public interface PFollowDao extends BaseMapper<PFollow> {

}

