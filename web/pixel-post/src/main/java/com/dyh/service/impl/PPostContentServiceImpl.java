package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PPostContentDao;
import com.dyh.entity.PPostContent;
import com.dyh.service.PPostContentService;
import org.springframework.stereotype.Service;

/**
 * 冒泡/文章内容(PPostContent)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:37:18
 */
@Service("pPostContentService")
public class PPostContentServiceImpl extends ServiceImpl<PPostContentDao, PPostContent> implements PPostContentService {

}

