package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PWalletStatementDao;
import com.dyh.entity.PWalletStatement;
import com.dyh.service.PWalletStatementService;
import org.springframework.stereotype.Service;

/**
 * 钱包流水(PWalletStatement)表服务实现类
 *
 * @author makejava
 * @since 2023-03-26 21:14:24
 */
@Service("pWalletStatementService")
public class PWalletStatementServiceImpl extends ServiceImpl<PWalletStatementDao, PWalletStatement> implements PWalletStatementService {

}

