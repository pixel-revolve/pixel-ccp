package com.dyh.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PWareDao;
import com.dyh.entity.PWare;
import com.dyh.entity.vo.PFareVo;
import com.dyh.entity.vo.PUserAddressVo;
import com.dyh.feign.PUserFeignService;
import com.dyh.service.PWareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 仓库(PWare)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@Service("pWareService")
public class PWareServiceImpl extends ServiceImpl<PWareDao, PWare> implements PWareService {

    @Resource
    PUserFeignService pUserFeignService;


    @Override
    public PFareVo getFare(Long addrId) {
        PFareVo PFareVo = new PFareVo();

        //收获地址的详细信息
        PUserAddressVo pUserAddressVo = pUserFeignService.getAddressInfoById(addrId).getData();

        if (pUserAddressVo != null) {
            //100分像素币作为我们的运费计算
            Long fare = 100L;
            PFareVo.setFare(fare);
            PFareVo.setAddress(pUserAddressVo);
            return PFareVo;
        }
        return null;
    }
}

