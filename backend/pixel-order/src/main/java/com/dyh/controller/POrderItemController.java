package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.POrderItem;
import com.dyh.service.POrderItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 订单商品(POrderItem)表控制层
 *
 * @author makejava
 * @since 2023-04-01 19:05:24
 */
@RestController
@RequestMapping("pOrderItem")
public class POrderItemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private POrderItemService pOrderItemService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pOrderItem 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<POrderItem> page, POrderItem pOrderItem) {
        return success(this.pOrderItemService.page(page, new QueryWrapper<>(pOrderItem)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pOrderItemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pOrderItem 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody POrderItem pOrderItem) {
        return success(this.pOrderItemService.save(pOrderItem));
    }

    /**
     * 修改数据
     *
     * @param pOrderItem 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody POrderItem pOrderItem) {
        return success(this.pOrderItemService.updateById(pOrderItem));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pOrderItemService.removeByIds(idList));
    }
}

