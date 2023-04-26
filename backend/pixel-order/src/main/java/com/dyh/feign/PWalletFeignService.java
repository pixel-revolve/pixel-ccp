package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.config.FeignConfig;
import com.dyh.entity.vo.POrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "pixel-wallet",configuration = FeignConfig.class)
public interface PWalletFeignService {


    String UNIFORM_CART_PREFIX="/api/pCart";

    @GetMapping(value = UNIFORM_CART_PREFIX+"/currentUserCartItems")
    R<List<POrderItemVo>> getCurrentCartItems();

}
