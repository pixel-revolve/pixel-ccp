package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PSkuSaleAttrValue;
import com.dyh.service.PSkuSaleAttrValueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品库存销售属性值(PSkuSaleAttrValue)表控制层
 *
 * @author makejava
 * @since 2023-04-23 17:18:12
 */
@RestController
@RequestMapping("/api/pSkuSaleAttrValue")
public class PSkuSaleAttrValueController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PSkuSaleAttrValueService pSkuSaleAttrValueService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pSkuSaleAttrValue 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PSkuSaleAttrValue> page, PSkuSaleAttrValue pSkuSaleAttrValue) {
        return success(this.pSkuSaleAttrValueService.page(page, new QueryWrapper<>(pSkuSaleAttrValue)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pSkuSaleAttrValueService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pSkuSaleAttrValue 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PSkuSaleAttrValue pSkuSaleAttrValue) {
        return success(this.pSkuSaleAttrValueService.save(pSkuSaleAttrValue));
    }

    /**
     * 修改数据
     *
     * @param pSkuSaleAttrValue 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PSkuSaleAttrValue pSkuSaleAttrValue) {
        return success(this.pSkuSaleAttrValueService.updateById(pSkuSaleAttrValue));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pSkuSaleAttrValueService.removeByIds(idList));
    }
}

