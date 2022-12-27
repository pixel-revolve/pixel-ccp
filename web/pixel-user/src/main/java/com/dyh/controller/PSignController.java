package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PSign;
import com.dyh.service.PSignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (PSign)表控制层
 *
 * @author makejava
 * @since 2022-11-23 14:10:42
 */
@RestController
@RequestMapping("pSign")
public class PSignController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PSignService pSignService;

    /**
     * 新增数据
     *
     * @param pSign 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public R insert(@RequestBody PSign pSign) {
        return success(this.pSignService.save(pSign));
    }

    /**
     * 修改数据
     *
     * @param pSign 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody PSign pSign) {
        return success(this.pSignService.updateById(pSign));
    }

}

