package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PSku;
import com.dyh.service.PSkuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品库存表(PSku)表控制层
 *
 * @author makejava
 * @since 2023-04-23 14:26:54
 */
@RestController
@RequestMapping("/api/pSku")
public class PSkuController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PSkuService pSkuService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pSku 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PSku> page, PSku pSku) {
        return success(this.pSkuService.page(page, new QueryWrapper<>(pSku)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pSkuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pSku 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PSku pSku) {
        return success(this.pSkuService.save(pSku));
    }

    /**
     * 修改数据
     *
     * @param pSku 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PSku pSku) {
        return success(this.pSkuService.updateById(pSku));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pSkuService.removeByIds(idList));
    }
}

