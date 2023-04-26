package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PSpuDao;
import com.dyh.entity.PSku;
import com.dyh.entity.PSpu;
import com.dyh.service.PSkuService;
import com.dyh.service.PSpuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 产品表(PSpu)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 18:54:53
 */
@Service("pSpuService")
public class PSpuServiceImpl extends ServiceImpl<PSpuDao, PSpu> implements PSpuService {

    @Resource
    PSkuService skuService;

    /**
     * spu信息通过skuId
     *
     * @param skuId sku id
     * @return {@link R}
     */
    @Override
    public R selectSpuInfoBySkuId(Long skuId) {
        //先查询sku表里的数据
        PSku getSku = skuService.getById(skuId);
        if (getSku == null) {
            return R.failed("查询为空");
        }
        //获得spuId
        Long spuId = getSku.getSpuId();
        //再通过spuId查询spuInfo信息表里的数据
        PSpu getPSpu = this.baseMapper.selectById(spuId);
        return R.ok(getPSpu);
    }

    /**
     * 得到价格
     *
     * @param skuId
     * @return {@link R}<{@link Long}>
     */
    @Override
    public R<Long> getPriceBySkuId(Long skuId) {
        //获取当前商品的信息
        PSku getSku = skuService.getById(skuId);
        if (getSku == null) {
            return R.failed("查询为空");
        }
        return R.ok(getSku.getPrice());
    }
}

