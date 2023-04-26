package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PAttr;
import com.dyh.service.PAttrService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品属性(PAttr)表控制层
 *
 * @author makejava
 * @since 2023-04-23 17:05:57
 */
@RestController
@RequestMapping("/api/pAttr")
public class PAttrController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PAttrService pAttrService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pAttr 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PAttr> page, PAttr pAttr) {
        return success(this.pAttrService.page(page, new QueryWrapper<>(pAttr)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pAttrService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pAttr 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PAttr pAttr) {
        return success(this.pAttrService.save(pAttr));
    }

    /**
     * 修改数据
     *
     * @param pAttr 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PAttr pAttr) {
        return success(this.pAttrService.updateById(pAttr));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pAttrService.removeByIds(idList));
    }
}

