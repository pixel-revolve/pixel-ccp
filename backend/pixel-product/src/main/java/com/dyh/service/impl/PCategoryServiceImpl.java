package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PCategoryDao;
import com.dyh.entity.PCategory;
import com.dyh.service.PCategoryService;
import org.springframework.stereotype.Service;

/**
 * 产品类别(PCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-04-23 15:21:34
 */
@Service("pCategoryService")
public class PCategoryServiceImpl extends ServiceImpl<PCategoryDao, PCategory> implements PCategoryService {

}

