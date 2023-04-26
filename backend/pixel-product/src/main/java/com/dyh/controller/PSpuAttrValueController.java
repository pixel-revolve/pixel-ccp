package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PSpuAttrValue;
import com.dyh.service.PSpuAttrValueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品属性值(PSpuAttrValue)表控制层
 *
 * @author makejava
 * @since 2023-04-23 17:17:15
 */
@RestController
@RequestMapping("/api/pSpuAttrValue")
public class PSpuAttrValueController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PSpuAttrValueService pSpuAttrValueService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pSpuAttrValue 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PSpuAttrValue> page, PSpuAttrValue pSpuAttrValue) {
        return success(this.pSpuAttrValueService.page(page, new QueryWrapper<>(pSpuAttrValue)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pSpuAttrValueService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pSpuAttrValue 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PSpuAttrValue pSpuAttrValue) {
        return success(this.pSpuAttrValueService.save(pSpuAttrValue));
    }

    /**
     * 修改数据
     *
     * @param pSpuAttrValue 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PSpuAttrValue pSpuAttrValue) {
        return success(this.pSpuAttrValueService.updateById(pSpuAttrValue));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pSpuAttrValueService.removeByIds(idList));
    }
}

