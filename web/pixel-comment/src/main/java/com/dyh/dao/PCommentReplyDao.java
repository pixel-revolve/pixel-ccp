package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PCommentReply;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论回复(PCommentReply)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 13:33:24
 */
@Mapper
public interface PCommentReplyDao extends BaseMapper<PCommentReply> {

}

