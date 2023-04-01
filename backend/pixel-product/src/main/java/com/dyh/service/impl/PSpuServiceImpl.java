package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PSpuDao;
import com.dyh.entity.PSpu;
import com.dyh.service.PSpuService;
import org.springframework.stereotype.Service;

/**
 * 产品表(PSpu)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 18:54:53
 */
@Service("pSpuService")
public class PSpuServiceImpl extends ServiceImpl<PSpuDao, PSpu> implements PSpuService {

}

