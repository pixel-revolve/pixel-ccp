package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PAttachment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件(PAttachment)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-20 12:37:41
 */
@Mapper
public interface PAttachmentDao extends BaseMapper<PAttachment> {

}

