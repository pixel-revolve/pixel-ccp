package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PPostDao;
import com.dyh.entity.PPost;
import com.dyh.service.PPostService;
import org.springframework.stereotype.Service;

/**
 * 冒泡/文章(PPost)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:35:24
 */
@Service("pPostService")
public class PPostServiceImpl extends ServiceImpl<PPostDao, PPost> implements PPostService {

}

