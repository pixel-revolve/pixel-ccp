package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PAttrDao;
import com.dyh.entity.PAttr;
import com.dyh.service.PAttrService;
import org.springframework.stereotype.Service;

/**
 * 产品属性(PAttr)表服务实现类
 *
 * @author makejava
 * @since 2023-04-23 17:05:57
 */
@Service("pAttrService")
public class PAttrServiceImpl extends ServiceImpl<PAttrDao, PAttr> implements PAttrService {

}

