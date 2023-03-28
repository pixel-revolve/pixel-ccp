package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PWalletStatement;
import org.apache.ibatis.annotations.Mapper;

/**
 * 钱包流水(PWalletStatement)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-26 21:14:24
 */
@Mapper
public interface PWalletStatementDao extends BaseMapper<PWalletStatement> {

}

