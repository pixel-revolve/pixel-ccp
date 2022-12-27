package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PPostCollectionDao;
import com.dyh.entity.PPostCollection;
import com.dyh.service.PPostCollectionService;
import org.springframework.stereotype.Service;

/**
 * 冒泡/文章收藏(PPostCollection)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:36:42
 */
@Service("pPostCollectionService")
public class PPostCollectionServiceImpl extends ServiceImpl<PPostCollectionDao, PPostCollection> implements PPostCollectionService {

}

