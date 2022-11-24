package com.dyh.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PPostDao;
import com.dyh.entity.PPost;
import com.dyh.service.PPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import static com.dyh.constant.RedisConstants.*;

/**
 * 冒泡/文章(PPost)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:35:24
 */
@Service("pPostService")
public class PPostServiceImpl extends ServiceImpl<PPostDao, PPost> implements PPostService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    ObjectMapper objectMapper;

    /**
     * 由于被继承的方法返回值为泛型不好擦除于是重新构造并且实现接口方法
     *
     * @param id id
     * @return {@link R}
     */
    public R getById(Long id) throws JsonProcessingException {
        String key=CACHE_POST_KEY+id;

        // 1.从redis查询文章缓存
        String postJson= stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if(StrUtil.isNotBlank(postJson)){
            try {
                PPost pPost=objectMapper.readValue(postJson,PPost.class);
                // 3.存在，直接返回
                return R.ok(pPost);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        // 判断命中的是否是空值
        if(postJson!=null){
            // 返回一个错误信息
            R.failed("缓存错误");
        }

        // 4.不存在则根据id查询数据库
        PPost pPost= super.getById(id);
        // 5.不存在，返回错误
        if(pPost==null){
            // 将空值写入到redis
            stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
            // 返回错误信息
            return R.failed("文章不存在！");
        }
        // 6.存在，写入redis，设置过期时间
        stringRedisTemplate.opsForValue().set(key,objectMapper.writeValueAsString(pPost),CACHE_POST_TTL, TimeUnit.MINUTES);
        // 7.返回
        return R.ok(pPost);
    }

}

