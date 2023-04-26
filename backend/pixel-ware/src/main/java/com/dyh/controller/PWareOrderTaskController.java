package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PWareOrderTask;
import com.dyh.service.PWareOrderTaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 库存工作单(PWareOrderTask)表控制层
 *
 * @author makejava
 * @since 2023-04-05 22:29:34
 */
@RestController
@RequestMapping("/api/pWareOrderTask")
public class PWareOrderTaskController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PWareOrderTaskService pWareOrderTaskService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pWareOrderTask 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PWareOrderTask> page, PWareOrderTask pWareOrderTask) {
        return success(this.pWareOrderTaskService.page(page, new QueryWrapper<>(pWareOrderTask)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pWareOrderTaskService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pWareOrderTask 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PWareOrderTask pWareOrderTask) {
        return success(this.pWareOrderTaskService.save(pWareOrderTask));
    }

    /**
     * 修改数据
     *
     * @param pWareOrderTask 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PWareOrderTask pWareOrderTask) {
        return success(this.pWareOrderTaskService.updateById(pWareOrderTask));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pWareOrderTaskService.removeByIds(idList));
    }
}

