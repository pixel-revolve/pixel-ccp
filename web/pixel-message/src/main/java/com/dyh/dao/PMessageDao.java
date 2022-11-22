package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息通知(PMessage)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 13:30:42
 */
@Mapper
public interface PMessageDao extends BaseMapper<PMessage> {

}

