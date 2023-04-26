package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("pixel-order")
public interface OrderFeignService {

    String UNIFORM_PREFIX="/api/pOrder";

    @GetMapping(value = UNIFORM_PREFIX+"/status/{orderId}")
    R getOrderStatus(@PathVariable("orderId") Long orderId);

}
