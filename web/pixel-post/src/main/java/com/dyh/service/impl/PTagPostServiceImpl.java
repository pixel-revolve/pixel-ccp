package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PTagPostDao;
import com.dyh.entity.PTagPost;
import com.dyh.service.PTagPostService;
import org.springframework.stereotype.Service;

/**
 * 标签文章关联表(PTagPost)表服务实现类
 *
 * @author makejava
 * @since 2022-11-26 10:30:30
 */
@Service("pTagPostService")
public class PTagPostServiceImpl extends ServiceImpl<PTagPostDao, PTagPost> implements PTagPostService {

}

