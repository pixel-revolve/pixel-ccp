package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PWareOrderTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存工作单(PWareOrderTask)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-05 22:29:34
 */
@Mapper
public interface PWareOrderTaskDao extends BaseMapper<PWareOrderTask> {

}

