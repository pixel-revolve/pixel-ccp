package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PSpuCategoryDao;
import com.dyh.entity.PSpuCategory;
import com.dyh.service.PSpuCategoryService;
import org.springframework.stereotype.Service;

/**
 * 产品类别(PSpuCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 18:58:08
 */
@Service("pSpuCategoryService")
public class PSpuCategoryServiceImpl extends ServiceImpl<PSpuCategoryDao, PSpuCategory> implements PSpuCategoryService {

}

