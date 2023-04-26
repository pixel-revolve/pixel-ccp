package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PWareOrderTaskDetailDao;
import com.dyh.entity.PWareOrderTaskDetail;
import com.dyh.service.PWareOrderTaskDetailService;
import org.springframework.stereotype.Service;

/**
 * 库存工作单细节(PWareOrderTaskDetail)表服务实现类
 *
 * @author makejava
 * @since 2023-04-05 22:29:34
 */
@Service("pWareOrderTaskDetailService")
public class PWareOrderTaskDetailServiceImpl extends ServiceImpl<PWareOrderTaskDetailDao, PWareOrderTaskDetail> implements PWareOrderTaskDetailService {

}

