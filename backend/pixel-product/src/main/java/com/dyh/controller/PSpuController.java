package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PSpu;
import com.dyh.service.PSpuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品表(PSpu)表控制层
 *
 * @author makejava
 * @since 2023-04-01 18:54:53
 */
@RestController
@RequestMapping("pSpu")
public class PSpuController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PSpuService pSpuService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pSpu 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PSpu> page, PSpu pSpu) {
        return success(this.pSpuService.page(page, new QueryWrapper<>(pSpu)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pSpuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pSpu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PSpu pSpu) {
        return success(this.pSpuService.save(pSpu));
    }

    /**
     * 修改数据
     *
     * @param pSpu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PSpu pSpu) {
        return success(this.pSpuService.updateById(pSpu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pSpuService.removeByIds(idList));
    }
}

