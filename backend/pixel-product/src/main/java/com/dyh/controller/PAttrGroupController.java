package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PAttrGroup;
import com.dyh.service.PAttrGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品属性组(PAttrGroup)表控制层
 *
 * @author makejava
 * @since 2023-04-23 17:07:41
 */
@RestController
@RequestMapping("/api/pAttrGroup")
public class PAttrGroupController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PAttrGroupService pAttrGroupService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pAttrGroup 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PAttrGroup> page, PAttrGroup pAttrGroup) {
        return success(this.pAttrGroupService.page(page, new QueryWrapper<>(pAttrGroup)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pAttrGroupService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pAttrGroup 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PAttrGroup pAttrGroup) {
        return success(this.pAttrGroupService.save(pAttrGroup));
    }

    /**
     * 修改数据
     *
     * @param pAttrGroup 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PAttrGroup pAttrGroup) {
        return success(this.pAttrGroupService.updateById(pAttrGroup));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pAttrGroupService.removeByIds(idList));
    }
}

