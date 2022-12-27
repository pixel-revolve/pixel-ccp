package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PSignDao;
import com.dyh.entity.PSign;
import com.dyh.service.PSignService;
import org.springframework.stereotype.Service;

/**
 * (PSign)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 14:10:42
 */
@Service("pSignService")
public class PSignServiceImpl extends ServiceImpl<PSignDao, PSign> implements PSignService {

}

