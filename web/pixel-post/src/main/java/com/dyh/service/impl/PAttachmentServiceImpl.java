package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PAttachmentDao;
import com.dyh.entity.PAttachment;
import com.dyh.service.PAttachmentService;
import org.springframework.stereotype.Service;

/**
 * 附件(PAttachment)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:37:41
 */
@Service("pAttachmentService")
public class PAttachmentServiceImpl extends ServiceImpl<PAttachmentDao, PAttachment> implements PAttachmentService {

}

