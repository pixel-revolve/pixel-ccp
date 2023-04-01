package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PWareSkuDao;
import com.dyh.entity.PWareSku;
import com.dyh.service.PWareSkuService;
import org.springframework.stereotype.Service;

/**
 * 产品库存(PWareSku)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@Service("pWareSkuService")
public class PWareSkuServiceImpl extends ServiceImpl<PWareSkuDao, PWareSku> implements PWareSkuService {

}

