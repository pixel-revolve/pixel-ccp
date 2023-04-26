package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PCategory;
import com.dyh.service.PCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品类别(PCategory)表控制层
 *
 * @author makejava
 * @since 2023-04-23 15:21:34
 */
@RestController
@RequestMapping("/api/pCategory")
public class PCategoryController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PCategoryService pCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pCategory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PCategory> page, PCategory pCategory) {
        return success(this.pCategoryService.page(page, new QueryWrapper<>(pCategory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PCategory pCategory) {
        return success(this.pCategoryService.save(pCategory));
    }

    /**
     * 修改数据
     *
     * @param pCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PCategory pCategory) {
        return success(this.pCategoryService.updateById(pCategory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pCategoryService.removeByIds(idList));
    }
}

