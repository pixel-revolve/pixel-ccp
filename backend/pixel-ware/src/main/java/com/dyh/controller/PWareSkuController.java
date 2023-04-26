package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PWareSku;
import com.dyh.entity.vo.PSkuHasStockVo;
import com.dyh.entity.vo.PWareSkuLockVo;
import com.dyh.service.PWareSkuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 产品库存(PWareSku)表控制层
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@RestController
@RequestMapping("/api/pWareSku")
public class PWareSkuController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private PWareSkuService pWareSkuService;

    /**
     * 查询sku是否有库存
     * @return
     */
    @PostMapping(value = "/getSkuHasStock")
    public R<List<PSkuHasStockVo>> getSkuHasStock(@RequestBody List<Long> skuIds) {
        return pWareSkuService.getSkuHasStock(skuIds);
    }

    /**
     * 锁定库存
     * @param vo
     *
     * 库存解锁的场景
     *      1）、下订单成功，订单过期没有支付被系统自动取消或者被用户手动取消，都要解锁库存
     *      2）、下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚。之前锁定的库存就要自动解锁
     *      3）、
     *
     * @return
     */
    @PostMapping(value = "/orderLockStock")
    public R orderLockStock(@RequestBody PWareSkuLockVo vo) {
        return pWareSkuService.orderLockStock(vo);
    }

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pWareSku 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PWareSku> page, PWareSku pWareSku) {
        return success(this.pWareSkuService.page(page, new QueryWrapper<>(pWareSku)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pWareSkuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pWareSku 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PWareSku pWareSku) {
        return success(this.pWareSkuService.save(pWareSku));
    }

    /**
     * 修改数据
     *
     * @param pWareSku 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PWareSku pWareSku) {
        return success(this.pWareSkuService.updateById(pWareSku));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pWareSkuService.removeByIds(idList));
    }
}

