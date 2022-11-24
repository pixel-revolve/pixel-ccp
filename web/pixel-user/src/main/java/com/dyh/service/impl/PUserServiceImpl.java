package com.dyh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PUserDao;
import com.dyh.entity.PSign;
import com.dyh.entity.PUser;

import static com.dyh.constant.RedisConstants.*;
import static com.dyh.constant.UserConstants.USERNAME_PREFIX;
import static com.dyh.constant.UserConstants.USER_NICKNAME_PREFIX;

import com.dyh.entity.dto.LoginFormDTO;
import com.dyh.entity.dto.PUserDTO;
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
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private PSignService pSignService;

    @Resource
    ObjectMapper objectMapper;

    @Override
    public R getByPhone(String phone) {
        PUser getUser = this.baseMapper.selectOne(new QueryWrapper<PUser>()
                .eq("phone", phone));
        boolean delete = isDelete(getUser);
        if (getUser == null || delete){
            return R.failed("找不到该用户");
        }

        return R.ok(getUser);
    }

    @Override
    public R getNicknameById(Long id) {
        PUser getUser = this.baseMapper.selectOne(new QueryWrapper<PUser>()
                .select("nickname").eq("id", id).eq("is_del",0));
        if (getUser == null){
            return R.failed("找不到该用户");
        }
        return R.ok(getUser.getNickname());
    }


    @Override
    public R createUserByPhone(String phone) {
        PUser user=new PUser();
        user.setPhone(phone);
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

        // 判断该用户是否已经被删除
        boolean delete = isDelete(getUser);
        if(delete) {
            return R.failed("该用户已经被删除");
        }

        // 5.判断用户是否存在
        if (getUser == null) {
            // 6.不存在，创建新用户并保存
            getUser = objectMapper.readValue(objectMapper.writeValueAsString(this.createUserByPhone(phone).getData()),PUser.class);
        }
        PUser user = getUser;

        // 7.保存用户信息到 redis中
        // 7.1.随机生成token，作为登录令牌
        String token = UUID.randomUUID().toString(true);
        // 改成jwt方式登录
        String jwt = JwtUtil.generate(getUser.getUsername());
        // 7.2.将User对象转为HashMap存储
        PUserDTO userDTO = BeanUtil.copyProperties(user, PUserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // 7.3.存储用户数据到redis
//        String tokenKey = LOGIN_USER_KEY + token;
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

    @Override
    public R sign() throws ParseException {
        // 1. 获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        // 2. 获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3. 拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        // 4. 获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        // 5. 写入Redis SETBIT key offset 1
        stringRedisTemplate.opsForValue().setBit(key,dayOfMonth - 1,true);

        // 存入数据库
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        QueryWrapper<PSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        PSign record = pSignService.getOne(queryWrapper);
        if(record==null) {
            // 没有记录，则新增
            PSign pSign = new PSign();
            pSign.setUserId(userId);
            pSign.setDate(sdf.format(date));
            pSign.setCount(1L);
            pSignService.save(pSign);
        } else {
            // 有记录了，判断距离上次签到的时间是否在一天以内
            int days = (int) ((new Date().getTime() - sdf.parse(sdf.format(date)).getTime()))/(1000*3600*24);
            Long count = record.getCount();
            count = days <=1 ? ++count : 0L;
            record.setCount(count);
            // 防止用户重复签到
            // 获取本月截止今天为止的所有的签到记录,返回的是一个十进制的数字
            List<Long> result = stringRedisTemplate.opsForValue().bitField(
                    key,
                    BitFieldSubCommands.create()
                            .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                            .valueAt(0)
            );
            if(result.get(result.toArray().length-1) == 0) {
                pSignService.updateById(record);
            } else {
                return R.failed("不能重复签到");
            }

        }
        return R.ok("签到成功");
    }

    /**
     * 获得当前用户连续签到次数
     */
    @Override
    public R signContinueCount() {
        // 1. 获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        QueryWrapper<PSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        PSign record = pSignService.getOne(queryWrapper);
        return R.ok(record.getCount());
    }

    /**
     * 获取当月累计签到次数
     * @return
     */
    @Override
    public R signMonthCount() {
        // 1. 获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        // 2. 获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3. 拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        // 4. 获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        // 5. 获取本月截止今天为止的所有的签到记录,返回的是一个十进制的数字
        List<Long> result = stringRedisTemplate.opsForValue().bitField(
                key,
                BitFieldSubCommands.create()
                        .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                        .valueAt(0)
        );
        if (result == null || result.isEmpty()) {
            // 没有任何签到结果
            return R.ok(0);
        }
        Long num = result.get(0);
        if (num == null || num == 0) {
            return R.ok(0);
        }
        // 6. 循环遍历
        int count = 0;
        while(true) {
            // 6.1. 让这个数字与1做与运算，得到数字的最后一个bit位,判断这个bit位是否为0
            if ((num & 1) == 0) {
                // 如果为0，说明未签到，结束
                break;
            } else {
                // 如果不为0，说明已经签到，计数器+1
                count ++;
            }
            // 把数字右移一位，抛弃最后一个bit位，继续下一个bit位
            num >>>= 1;
        }
        return R.ok(count);
    }




}

