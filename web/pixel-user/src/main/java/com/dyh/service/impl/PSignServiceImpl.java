package com.dyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PSignDao;
import com.dyh.entity.PSign;
import com.dyh.service.PSignService;
import com.dyh.utils.UserHolder;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.dyh.constant.RedisConstants.USER_SIGN_KEY;

/**
 * (PSign)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 14:10:42
 */
@Service("pSignService")
public class PSignServiceImpl extends ServiceImpl<PSignDao, PSign> implements PSignService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public R sign() {
        // 1. 获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        // 2. 获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3. 拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        // 4. 获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        Boolean bit = stringRedisTemplate.opsForValue().getBit(key, dayOfMonth - 1);
        if(Boolean.TRUE.equals(bit))
            return R.failed("今天已经签过了");
        // 5. 写入Redis SETBIT key offset 1
        stringRedisTemplate.opsForValue().setBit(key,dayOfMonth - 1,true);
        return R.ok("签到成功");
    }

    @Override
    public R signWithMySQL(){
        // 1. 获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        PSign record = this.baseMapper.selectOne(new QueryWrapper<PSign>().eq("user_id",userId));
        if(record==null) {
            // 没有记录，则新增
            PSign pSign = new PSign();
            pSign.setUserId(userId);
            pSign.setDate(sdf.format(date));
            pSign.setCount(1L);
            save(pSign);
        }else {
            if(Objects.equals(record.getDate(), sdf.format(date))){
                return R.failed("今天已经签过了");
            }
            record.setDate(sdf.format(date));
            Long count = record.getCount();
            record.setCount(++count);
            record.updateById();
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
        PSign record = this.baseMapper.selectOne(queryWrapper);
        if(record==null)
            return R.failed("当前用户无签到记录");
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

