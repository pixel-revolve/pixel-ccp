package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PMessageDao;
import com.dyh.entity.PMessage;
import com.dyh.service.PMessageService;
import org.springframework.stereotype.Service;

/**
 * 消息通知(PMessage)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 13:30:42
 */
@Service("pMessageService")
public class PMessageServiceImpl extends ServiceImpl<PMessageDao, PMessage> implements PMessageService {

}

