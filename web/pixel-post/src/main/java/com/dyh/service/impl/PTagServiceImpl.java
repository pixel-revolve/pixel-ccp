package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PTagDao;
import com.dyh.entity.PTag;
import com.dyh.service.PTagService;
import org.springframework.stereotype.Service;

/**
 * 标签(PTag)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:36:57
 */
@Service("pTagService")
public class PTagServiceImpl extends ServiceImpl<PTagDao, PTag> implements PTagService {

}

