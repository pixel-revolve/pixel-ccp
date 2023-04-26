package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PSkuDao;
import com.dyh.entity.PSku;
import com.dyh.service.PSkuService;
import org.springframework.stereotype.Service;

/**
 * 产品库存表(PSku)表服务实现类
 *
 * @author makejava
 * @since 2023-04-23 14:26:54
 */
@Service("pSkuService")
public class PSkuServiceImpl extends ServiceImpl<PSkuDao, PSku> implements PSkuService {

}

