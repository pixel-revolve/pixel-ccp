package com.dyh.interceptor;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.dyh.entity.dto.PUserDTO;
import com.dyh.entity.po.PUser;
import com.dyh.feign.PUserFeignService;
import com.dyh.utils.JwtUtil;
import com.dyh.utils.UserHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截器
 *
 * @author pixel-revolve
 * @date 2022/11/20
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {

    @Resource
    private PUserFeignService pUserFeignService;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 判断是否需要拦截（ThreadLocal中是否有用户）
        if (UserHolder.getUser() != null) {
            // 从请求头中获取token字符串并解析
            Claims claims = JwtUtil.parse(request.getHeader("Authorization"));
            // 已登录就直接放行
            if (claims != null) {
                // 将我们之前放到token中的userName给存到上下文对象中
                String username = claims.getSubject();
                // 用 username 查询用户组装 UserDTO 并存储到 UserHolder 中
                R res = pUserFeignService.selectByUsername(username);
                if(res.getCode()== ApiErrorCode.FAILED.getCode()){// 如果在查询中出错则直接返回错误信息
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json;charset=utf-8");
                    // 序列化结果对象为JSON
                    String resultJSON = JSON.toJSONString(res);
                    // 写入响应体
                    PrintWriter writer = response.getWriter();
                    writer.write(resultJSON);
                    writer.flush();
                    writer.close();
                    return false;
                }
                // 反序列化得到PTag完整信息
                PUser getPUser=objectMapper.readValue(objectMapper.writeValueAsString(res.getData()), PUser.class);
                PUserDTO pUserDTO = new PUserDTO();
                pUserDTO.setNickname(getPUser.getNickname());
                pUserDTO.setId(getPUser.getId());
                UserHolder.saveUser(pUserDTO);
                return true;
            }
        }

        // 走到这里就代表是其他接口，且没有登录
        // 设置响应数据类型为json（前后端分离）
        // 没有，需要拦截，设置状态码 设定响应状态码为403
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        // 序列化结果对象为JSON
        String resultJSON = JSON.toJSONString(R.failed("未登录"));
        // 写入响应体
        PrintWriter writer = response.getWriter();
        writer.write(resultJSON);
        writer.flush();
        writer.close();
        // 拦截
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception {
        // 请求结束后要从上下文对象删除数据，如果不删除则可能会导致内存泄漏
        UserHolder.removeUser();
        super.afterCompletion(request,response,handler,ex);
    }

}
