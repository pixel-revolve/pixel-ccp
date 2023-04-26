package com.dyh.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.entity.vo.PCartItemVo;
import com.dyh.feign.PProductFeignService;
import com.dyh.service.PCartService;
import com.dyh.utils.UserHolder;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.dyh.constant.RedisConstants.CART_KEY;

@Service
public class PCartServiceImpl implements PCartService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private PProductFeignService productFeignService;

    /**
     * 获取当前用户的购物车条目
     *
     * @return {@link R}<{@link List}<{@link PCartItemVo}>>
     */
    @Override
    public R<List<PCartItemVo>> getUserCartItems() {
        List<PCartItemVo> cartItemVoList;
        //获取当前用户登录的信息
        Long userId = UserHolder.getUser().getId();
        //如果用户未登录直接返回null
        if (userId == null) {
            return null;
        } else {
            //获取购物车项
            String cartKey = CART_KEY + userId;
            //获取所有的
            List<PCartItemVo> cartItems = getCartItems(cartKey);
            if (cartItems == null) {
                throw new RuntimeException();
            }
            //筛选出选中的
            cartItemVoList = cartItems.stream()
                    .filter(PCartItemVo::getCheck)
                    .peek(item -> {
                        //更新为最新的价格（查询数据库）
                        Long price = productFeignService.getPriceBySkuId(item.getSkuId()).getData();
                        item.setPrice(price);
                    })
                    .collect(Collectors.toList());
        }

        return R.ok(cartItemVoList);
    }

    /**
     * 获取购物车里面的数据
     * @param cartKey
     * @return
     */
    private List<PCartItemVo> getCartItems(String cartKey) {
        //获取购物车里面的所有商品
        BoundHashOperations<String, Object, Object> operations = stringRedisTemplate.boundHashOps(cartKey);
        List<Object> values = operations.values();
        if (values != null && values.size() > 0) {
            // 收集购物车物品
            return values.stream().map((obj) -> {
                String str = (String) obj;
                return JSON.parseObject(str, PCartItemVo.class);
            }).collect(Collectors.toList());
        }
        return null;
    }
}
