package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PWareOrderTaskDao;
import com.dyh.entity.PWareOrderTask;
import com.dyh.service.PWareOrderTaskService;
import org.springframework.stereotype.Service;

/**
 * 库存工作单(PWareOrderTask)表服务实现类
 *
 * @author makejava
 * @since 2023-04-05 22:29:34
 */
@Service("pWareOrderTaskService")
public class PWareOrderTaskServiceImpl extends ServiceImpl<PWareOrderTaskDao, PWareOrderTask> implements PWareOrderTaskService {

}

