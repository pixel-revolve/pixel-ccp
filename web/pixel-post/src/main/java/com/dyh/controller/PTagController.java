package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PTag;
import com.dyh.service.PTagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 标签(PTag)表控制层
 *
 * @author makejava
 * @since 2022-11-20 12:36:57
 */
@RestController
@RequestMapping("/api/pTag")
public class PTagController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PTagService pTagService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pTag 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PTag> page, PTag pTag) {
        return success(this.pTagService.page(page, new QueryWrapper<>(pTag)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pTagService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pTag 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PTag pTag) {
        return success(this.pTagService.save(pTag));
    }

    /**
     * 修改数据
     *
     * @param pTag 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PTag pTag) {
        return success(this.pTagService.updateById(pTag));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pTagService.removeByIds(idList));
    }
}

