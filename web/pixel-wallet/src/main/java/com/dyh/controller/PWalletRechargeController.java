package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PWalletRecharge;
import com.dyh.service.PWalletRechargeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 钱包流水(PWalletRecharge)表控制层
 *
 * @author makejava
 * @since 2022-11-20 13:24:40
 */
@RestController
@RequestMapping("/api/pWalletRecharge/recharge")
public class PWalletRechargeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PWalletRechargeService pWalletRechargeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pWalletRecharge 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<PWalletRecharge> page, PWalletRecharge pWalletRecharge) {
        return success(this.pWalletRechargeService.page(page, new QueryWrapper<>(pWalletRecharge)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pWalletRechargeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pWalletRecharge 实体对象
     * @return 新增结果
     */
    @PostMapping("/save")
    public R insert(@RequestBody PWalletRecharge pWalletRecharge) {
        return success(this.pWalletRechargeService.save(pWalletRecharge));
    }

    /**
     * 修改数据
     *
     * @param pWalletRecharge 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody PWalletRecharge pWalletRecharge) {
        return success(this.pWalletRechargeService.updateById(pWalletRecharge));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pWalletRechargeService.removeByIds(idList));
    }
}

