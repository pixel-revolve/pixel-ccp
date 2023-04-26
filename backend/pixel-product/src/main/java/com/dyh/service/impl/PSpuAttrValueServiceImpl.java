package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PSpuAttrValueDao;
import com.dyh.entity.PSpuAttrValue;
import com.dyh.service.PSpuAttrValueService;
import org.springframework.stereotype.Service;

/**
 * 产品属性值(PSpuAttrValue)表服务实现类
 *
 * @author makejava
 * @since 2023-04-23 17:17:15
 */
@Service("pSpuAttrValueService")
public class PSpuAttrValueServiceImpl extends ServiceImpl<PSpuAttrValueDao, PSpuAttrValue> implements PSpuAttrValueService {

}

