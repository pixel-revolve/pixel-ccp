package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户(PUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-15 11:12:57
 */
@Mapper
public interface PUserDao extends BaseMapper<PUser> {

}

