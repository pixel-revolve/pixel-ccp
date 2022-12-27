package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论(PComment)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 13:33:01
 */
@Mapper
public interface PCommentDao extends BaseMapper<PComment> {

}

