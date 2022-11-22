package com.dyh.controller;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.entity.dto.LoginFormDTO;
import com.dyh.entity.dto.PUserDTO;
import com.dyh.entity.po.PUser;
import com.dyh.feign.PUserFeignService;
import com.dyh.utils.RegexUtils;
import com.dyh.utils.UserHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static com.dyh.constant.RedisConstants.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class LoginController extends ApiController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private PUserFeignService pUserFeignService;

    @Resource
    private ObjectMapper objectMapper;

    @PostMapping("/sendCode")
    public R sendCode(@RequestParam("phone") String phone){
        // 1.校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return R.failed("手机号格式错误！");
        }
        // 3.符合，生成验证码
        String code = RandomUtil.randomNumbers(6);

        // 4.保存验证码到 redis
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);

        // 5.发送验证码
        log.debug("发送短信验证码成功，验证码：{}", code);
        // 6.返回ok
        return success("发送验证码成功！");
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginFormDTO loginForm) throws JsonProcessingException {
        //远程登录
        return pUserFeignService.login(loginForm);
    }

    @PostMapping("/logout")
    public R logout(HttpServletRequest request) {
        return pUserFeignService.logout(request);
    }


}
