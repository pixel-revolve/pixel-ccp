package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PWalletStatement;
import com.dyh.service.PWalletStatementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 钱包流水(PWalletStatement)表控制层
 *
 * @author makejava
 * @since 2023-03-26 21:14:24
 */
@RestController
@RequestMapping("pWalletStatement")
public class PWalletStatementController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PWalletStatementService pWalletStatementService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pWalletStatement 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PWalletStatement> page, PWalletStatement pWalletStatement) {
        return success(this.pWalletStatementService.page(page, new QueryWrapper<>(pWalletStatement)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pWalletStatementService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pWalletStatement 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PWalletStatement pWalletStatement) {
        return success(this.pWalletStatementService.save(pWalletStatement));
    }

    /**
     * 修改数据
     *
     * @param pWalletStatement 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PWalletStatement pWalletStatement) {
        return success(this.pWalletStatementService.updateById(pWalletStatement));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pWalletStatementService.removeByIds(idList));
    }
}

