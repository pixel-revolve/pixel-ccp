package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PWalletRecharge;
import org.apache.ibatis.annotations.Mapper;

/**
 * 钱包流水(PWalletRecharge)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 13:24:40
 */
@Mapper
public interface PWalletRechargeDao extends BaseMapper<PWalletRecharge> {

}

