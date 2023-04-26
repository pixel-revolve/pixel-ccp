package com.dyh.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.entity.vo.PCartItemVo;
import com.dyh.service.PCartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/pCart")
public class PCartController {

    @Resource
    private PCartService cartService;

    /**
     * 获取当前用户的购物车商品项
     * @return
     */
    @GetMapping(value = "/currentUserCartItems")
    public R<List<PCartItemVo>> getCurrentCartItems() {
        return cartService.getUserCartItems();
    }

}
