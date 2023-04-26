package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "pixel-product",configuration = FeignConfig.class)
public interface PProductFeignService {
    String UNIFORM_PREFIX="/api/pProduct";

    @GetMapping(UNIFORM_PREFIX+"/selectSpuInfoBySkuId/{skuId}")
    R selectSpuInfoBySkuId(@PathVariable("skuId") Long skuId);

}
