package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PWareOrderTaskDetail;
import com.dyh.service.PWareOrderTaskDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 库存工作单细节(PWareOrderTaskDetail)表控制层
 *
 * @author makejava
 * @since 2023-04-05 22:29:34
 */
@RestController
@RequestMapping("/api/pWareOrderTaskDetail")
public class PWareOrderTaskDetailController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PWareOrderTaskDetailService pWareOrderTaskDetailService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pWareOrderTaskDetail 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PWareOrderTaskDetail> page, PWareOrderTaskDetail pWareOrderTaskDetail) {
        return success(this.pWareOrderTaskDetailService.page(page, new QueryWrapper<>(pWareOrderTaskDetail)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pWareOrderTaskDetailService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pWareOrderTaskDetail 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PWareOrderTaskDetail pWareOrderTaskDetail) {
        return success(this.pWareOrderTaskDetailService.save(pWareOrderTaskDetail));
    }

    /**
     * 修改数据
     *
     * @param pWareOrderTaskDetail 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PWareOrderTaskDetail pWareOrderTaskDetail) {
        return success(this.pWareOrderTaskDetailService.updateById(pWareOrderTaskDetail));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pWareOrderTaskDetailService.removeByIds(idList));
    }
}

