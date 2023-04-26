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

    String FOLLOW_PREFIX="/api/pFollow";
    @GetMapping(FOLLOW_PREFIX+"/queryFansById/{id}")
    R<List<PFollow>> queryFansById(@PathVariable("id") Long id);


    String USER_ADDRESS_PREFIX="/api/pUserReceiveAddress";
    /**
     * 查询当前用户的全部收货地址
     * @param userId
     * @return
     */
    @GetMapping(value = USER_ADDRESS_PREFIX+"/getAddress/{userId}")
    List<PUserAddressVo> getAddress(@PathVariable("userId") Long userId);
}
