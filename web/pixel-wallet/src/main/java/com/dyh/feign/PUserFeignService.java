package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "pixel-user",configuration = FeignConfig.class)
public interface PUserFeignService {
    String UNIFORM_PREFIX="/api/pUser";

    @GetMapping(UNIFORM_PREFIX+"/selectByUsername/{username}")
    R selectByUsername(@PathVariable String username);

    @PostMapping(UNIFORM_PREFIX+"/rechargeById/{id}/{amount}")
    R rechargeById(@PathVariable Long id,@PathVariable Long amount);

}
