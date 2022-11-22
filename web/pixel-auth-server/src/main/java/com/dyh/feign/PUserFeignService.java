package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.entity.dto.LoginFormDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@FeignClient("pixel-user")
public interface PUserFeignService {
    String UNIFORM_PREFIX="/api/pUser";

    @PostMapping(value = UNIFORM_PREFIX+"/login")
    R login(@RequestBody LoginFormDTO loginFormDTO);

    @PostMapping(UNIFORM_PREFIX+"/logout")
    R logout(HttpServletRequest request);

}
