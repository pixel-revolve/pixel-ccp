package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PUser;
import com.dyh.entity.dto.LoginFormDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户(PUser)表服务接口
 *
 * @author makejava
 * @since 2022-11-15 11:12:57
 */
public interface PUserService extends IService<PUser> {
    R getByPhone(String phone);

    R createUserByPhone(String phone);

    R login(LoginFormDTO loginFormDTO) throws JsonProcessingException;

    boolean isLogin(HttpServletRequest request);

    boolean isDelete(PUser pUser);
}

