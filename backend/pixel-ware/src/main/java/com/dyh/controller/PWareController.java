package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PWare;
import com.dyh.service.PWareService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 仓库(PWare)表控制层
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@RestController
@RequestMapping("/api/pWare")
public class PWareController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PWareService pWareService;

    /**
     * 获取运费信息
     * @return
     */
    @GetMapping(value = "/getFare")
    public R getFare(@RequestParam("addrId") Long addrId) {
        return success(this.pWareService.getFare(addrId));
    }

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pWare 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PWare> page, PWare pWare) {
        return success(this.pWareService.page(page, new QueryWrapper<>(pWare)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pWareService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pWare 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PWare pWare) {
        return success(this.pWareService.save(pWare));
    }

    /**
     * 修改数据
     *
     * @param pWare 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PWare pWare) {
        return success(this.pWareService.updateById(pWare));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pWareService.removeByIds(idList));
    }
}

