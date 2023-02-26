package com.dyh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PUserDao;
import com.dyh.entity.PSign;
import com.dyh.entity.PUser;

import static com.dyh.constant.RedisConstants.*;
import static com.dyh.constant.UserConstants.*;

import com.dyh.entity.dto.LoginFormDTO;
import com.dyh.entity.dto.PUserDTO;
import com.dyh.entity.vo.PUserMeVo;
import com.dyh.service.PSignService;
import com.dyh.service.PUserService;
import com.dyh.utils.JwtUtil;
import com.dyh.utils.RedisIdWorker;
import com.dyh.utils.RegexUtils;
import com.dyh.utils.UserHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户(PUser)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 11:12:57
 */
@Service("pUserService")
public class PUserServiceImpl extends ServiceImpl<PUserDao, PUser> implements PUserService {

    @Resource
    RedisIdWorker redisIdWorker;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    ObjectMapper objectMapper;

    @Override
    public R getByPhone(String phone) {
        PUser getUser = this.baseMapper.selectOne(new QueryWrapper<PUser>()
                .eq("phone", phone));
        if (getUser == null){
            return R.failed("找不到该用户");
        }

        return R.ok(getUser);
    }

    @Override
    public R getByUsername(String username) {
        PUser getUser=this.baseMapper.selectOne(new QueryWrapper<PUser>()
                .eq("username",username));
        if(getUser == null){
            return R.failed("找不到该用户");
        }
        return R.ok(getUser);
    }

    @Override
    public R me() {
        Long id = UserHolder.getUser().getId();
        PUser getUser = this.baseMapper.selectById(id);
        if(getUser==null){
            return R.failed("找不到该用户");
        }
        PUserMeVo pUserMeVo = BeanUtil.copyProperties(getUser, PUserMeVo.class);
        return R.ok(pUserMeVo);
    }


    @Override
    public R getNicknameById(Long id) {
        PUser getUser = this.baseMapper.selectOne(new QueryWrapper<PUser>()
                .select("nickname").eq("id", id));
        if (getUser == null){
            return R.failed("找不到该用户");
        }
        return R.ok(getUser.getNickname());
    }


    @Override
    public R createUserByPhone(String phone) {
        PUser user=new PUser();
        user.setPhone(phone);
        user.setId(redisIdWorker.nextId(USER_PREFIX));
        user.setUsername(String.valueOf(redisIdWorker.nextId(USERNAME_PREFIX)));
        user.setNickname(USER_NICKNAME_PREFIX + RandomUtil.randomString(10));
        save(user);
        return R.ok(user);
    }

    public R login(LoginFormDTO loginForm) throws JsonProcessingException {
        // 1.校验手机号
        String phone = loginForm.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return R.failed("手机号格式错误！");
        }
        // 3.从redis获取验证码并校验
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        String code = loginForm.getCode();
        if (cacheCode == null || !cacheCode.equals(code)) {
            // 不一致，报错
            return R.failed("验证码错误");
        }

        // 4.一致，根据手机号查询用户 select * from tb_user where phone = ?
        PUser getUser = query().eq("phone", phone).one();
        //Object getUser=pUserFeignService.selectByPhone(phone).getData();

        // 5.判断用户是否存在
        if (getUser == null) {
            // 6.不存在，创建新用户并保存
            getUser = objectMapper.readValue(objectMapper.writeValueAsString(this.createUserByPhone(phone).getData()),PUser.class);
        }
        PUser user = getUser;

        // 7.保存用户信息到 redis中
        // 7.1. 改成jwt方式登录
        String jwt = JwtUtil.generate(getUser.getUsername());
        // 7.2.将User对象转为HashMap存储
        PUserDTO userDTO = BeanUtil.copyProperties(user, PUserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // 7.3.存储用户数据到redis
        String tokenKey = LOGIN_USER_KEY + jwt;
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        // 7.4.设置token有效期
        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);

        // 8.返回token
        return R.ok(jwt);
    }

    @Override
    public R logout(HttpServletRequest request) {
        // 从请求头中获取token字符串
        String jwt = request.getHeader("Authorization");
        String tokenKey = LOGIN_USER_KEY + jwt;
        stringRedisTemplate.delete(tokenKey);
        return R.ok("登出成功");
    }

    @Override
    public boolean isLogin(HttpServletRequest request) {
        // 从请求头中获取token字符串
        String jwt = request.getHeader("Authorization");
        // 解析失败就提示用户登录
        if(JwtUtil.parse(jwt) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isDelete(PUser pUser) {
        if(pUser.getIsDel()==0) {
            return false;
        }
        return true;
    }


}

