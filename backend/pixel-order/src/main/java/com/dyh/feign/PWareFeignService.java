package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.entity.vo.PSkuHasStockVo;
import com.dyh.entity.vo.PWareSkuLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 库存Feign
 *
 * @author pixel-revolve
 * @date 2023/04/11
 */
@FeignClient("pixel-ware")
public interface PWareFeignService {

    String UNIFORM_PREFIX = "/api/pWare";

    /**
     * 查询运费和收货地址信息
     *
     * @param addrId
     * @return
     */
    @GetMapping(UNIFORM_PREFIX + "/getFare")
    R getFare(@RequestParam("addrId") Long addrId);


    String UNIFORM_WARE_SKU_PREFIX = "/api/pWareSku";

    /**
     * 锁定库存
     *
     * @param vo
     * @return
     */
    @PostMapping(value = UNIFORM_WARE_SKU_PREFIX + "/orderLockStock")
    R orderLockStock(@RequestBody PWareSkuLockVo vo);

    /**
     * 查询sku是否有库存
     *
     * @return
     */
    @PostMapping(value = UNIFORM_WARE_SKU_PREFIX+"/getSkuHasStock")
    R<List<PSkuHasStockVo>> getSkuHasStock(@RequestBody List<Long> skuIds);

}
