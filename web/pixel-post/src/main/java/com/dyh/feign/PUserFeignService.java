package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "pixel-user",configuration = FeignConfig.class)
public interface PUserFeignService {
    String UNIFORM_PREFIX="/api/pUser";

    @GetMapping(UNIFORM_PREFIX+"/selectNicknameById/{id}")
    R selectNicknameById(@PathVariable Long id);

    @GetMapping(UNIFORM_PREFIX+"/selectByUsername/{username}")
    R selectByUsername(@PathVariable String username);
}
