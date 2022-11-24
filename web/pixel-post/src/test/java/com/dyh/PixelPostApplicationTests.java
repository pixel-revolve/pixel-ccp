package com.dyh;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PPost;
import com.dyh.service.impl.PPostServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PixelPostApplicationTests {

    @Resource
    PPostServiceImpl pPostService;


    @Test
    public void testPage() {
        System.out.println("----- selectPage method test ------");
        //分页参数
        Page<PPost> page = new Page<>(1,3);
        //queryWrapper组装查询where条件
        LambdaQueryWrapper<PPost> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PPost::getCommentCount,0);
        pPostService.getBaseMapper().selectPage(page,queryWrapper);
        page.getRecords().forEach(System.out::println);
    }

}
