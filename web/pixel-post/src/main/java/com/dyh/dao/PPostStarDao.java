package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PPostStar;
import org.apache.ibatis.annotations.Mapper;

/**
 * 冒泡/文章点赞(PPostStar)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 12:36:12
 */
@Mapper
public interface PPostStarDao extends BaseMapper<PPostStar> {

}

