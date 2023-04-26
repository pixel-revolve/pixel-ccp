package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PUserReceiveAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户收货地址表
(PUserReceiveAddress)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-11 22:19:57
 */
@Mapper
public interface PUserReceiveAddressDao extends BaseMapper<PUserReceiveAddress> {

}

