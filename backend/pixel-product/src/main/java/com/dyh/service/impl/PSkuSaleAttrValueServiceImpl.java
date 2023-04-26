package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PSkuSaleAttrValueDao;
import com.dyh.entity.PSkuSaleAttrValue;
import com.dyh.service.PSkuSaleAttrValueService;
import org.springframework.stereotype.Service;

/**
 * 产品库存销售属性值(PSkuSaleAttrValue)表服务实现类
 *
 * @author makejava
 * @since 2023-04-23 17:18:12
 */
@Service("pSkuSaleAttrValueService")
public class PSkuSaleAttrValueServiceImpl extends ServiceImpl<PSkuSaleAttrValueDao, PSkuSaleAttrValue> implements PSkuSaleAttrValueService {

}

