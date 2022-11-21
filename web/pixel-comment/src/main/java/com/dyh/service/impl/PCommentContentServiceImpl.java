package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PCommentContentDao;
import com.dyh.entity.PCommentContent;
import com.dyh.service.PCommentContentService;
import org.springframework.stereotype.Service;

/**
 * 评论内容(PCommentContent)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 13:33:14
 */
@Service("pCommentContentService")
public class PCommentContentServiceImpl extends ServiceImpl<PCommentContentDao, PCommentContent> implements PCommentContentService {

}

