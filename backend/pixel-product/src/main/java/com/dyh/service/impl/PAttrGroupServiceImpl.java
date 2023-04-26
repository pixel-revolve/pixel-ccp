package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PAttrGroupDao;
import com.dyh.entity.PAttrGroup;
import com.dyh.service.PAttrGroupService;
import org.springframework.stereotype.Service;

/**
 * 产品属性组(PAttrGroup)表服务实现类
 *
 * @author makejava
 * @since 2023-04-23 17:07:41
 */
@Service("pAttrGroupService")
public class PAttrGroupServiceImpl extends ServiceImpl<PAttrGroupDao, PAttrGroup> implements PAttrGroupService {

}

