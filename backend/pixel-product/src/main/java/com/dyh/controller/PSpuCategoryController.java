package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PSpuCategory;
import com.dyh.service.PSpuCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品类别(PSpuCategory)表控制层
 *
 * @author makejava
 * @since 2023-04-01 18:58:08
 */
@RestController
@RequestMapping("pSpuCategory")
public class PSpuCategoryController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PSpuCategoryService pSpuCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pSpuCategory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PSpuCategory> page, PSpuCategory pSpuCategory) {
        return success(this.pSpuCategoryService.page(page, new QueryWrapper<>(pSpuCategory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pSpuCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pSpuCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PSpuCategory pSpuCategory) {
        return success(this.pSpuCategoryService.save(pSpuCategory));
    }

    /**
     * 修改数据
     *
     * @param pSpuCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PSpuCategory pSpuCategory) {
        return success(this.pSpuCategoryService.updateById(pSpuCategory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pSpuCategoryService.removeByIds(idList));
    }
}

