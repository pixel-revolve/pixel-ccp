package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PWareDao;
import com.dyh.entity.PWare;
import com.dyh.service.PWareService;
import org.springframework.stereotype.Service;

/**
 * 仓库(PWare)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@Service("pWareService")
public class PWareServiceImpl extends ServiceImpl<PWareDao, PWare> implements PWareService {

}

