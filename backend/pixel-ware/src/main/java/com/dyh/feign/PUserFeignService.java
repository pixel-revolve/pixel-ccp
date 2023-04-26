package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.config.FeignConfig;
import com.dyh.entity.po.PFollow;
import com.dyh.entity.vo.PUserAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.util.List;

@FeignClient(value = "pixel-user",configuration = FeignConfig.class)
public interface PUserFeignService {
    String UNIFORM_PREFIX="/api/pUser";

    @GetMapping(UNIFORM_PREFIX+"/selectNicknameById/{id}")
    R selectNicknameById(@PathVariable Long id);

    @GetMapping(UNIFORM_PREFIX+"/selectOne/{id}")
    R selectOne(@PathVariable Serializable id);

    @GetMapping(UNIFORM_PREFIX+"/selectByUsername/{username}")
    R selectByUsername(@PathVariable String username);

    String UNIFORM_FOLLOW_PREFIX ="/api/pFollow";
    @GetMapping(UNIFORM_FOLLOW_PREFIX +"/queryFansById/{id}")
    R<List<PFollow>> queryFansById(@PathVariable("id") Long id);

    String UNIFORM_ADDRESS_PREFIX="/api/pUserReceiveAddress";
    @GetMapping(UNIFORM_ADDRESS_PREFIX+"/getAddressInfoById/{id}")
    R<PUserAddressVo> getAddressInfoById(@PathVariable("id")Long id);
}
