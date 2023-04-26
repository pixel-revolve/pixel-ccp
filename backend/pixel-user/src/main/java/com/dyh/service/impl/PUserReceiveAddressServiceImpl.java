package com.dyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PUserReceiveAddressDao;
import com.dyh.entity.PUserReceiveAddress;
import com.dyh.service.PUserReceiveAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户收货地址表
(PUserReceiveAddress)表服务实现类
 *
 * @author makejava
 * @since 2023-04-11 22:19:57
 */
@Service("pUserReceiveAddressService")
public class PUserReceiveAddressServiceImpl extends ServiceImpl<PUserReceiveAddressDao, PUserReceiveAddress> implements PUserReceiveAddressService {

    @Override
    public List<PUserReceiveAddress> getAddress(Long userId) {
        return this.baseMapper.selectList
                (new QueryWrapper<PUserReceiveAddress>().eq("user_id", userId));
    }
}

