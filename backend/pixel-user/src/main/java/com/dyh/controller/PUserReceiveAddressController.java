package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PUserReceiveAddress;
import com.dyh.entity.vo.PUserAddressVo;
import com.dyh.service.PUserReceiveAddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户收货地址表
(PUserReceiveAddress)表控制层
 *
 * @author makejava
 * @since 2023-04-11 22:19:57
 */
@RestController
@RequestMapping("/api/pUserReceiveAddress")
public class PUserReceiveAddressController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PUserReceiveAddressService pUserReceiveAddressService;

    /**
     * 信息
     */
    @RequestMapping("/getAddressInfoById/{id}")
    public R<PUserReceiveAddress> getAddressInfoById(@PathVariable("id") Long id){
        return R.ok(pUserReceiveAddressService.getById(id));
    }

    /**
     * 查询当前用户的全部收货地址
     * @param userId
     * @return
     */
    @GetMapping("/getAddress/{userId}")
    List<PUserReceiveAddress> getAddress(@PathVariable("userId") Long userId){
        return this.pUserReceiveAddressService.getAddress(userId);
    }

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pUserReceiveAddress 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PUserReceiveAddress> page, PUserReceiveAddress pUserReceiveAddress) {
        return success(this.pUserReceiveAddressService.page(page, new QueryWrapper<>(pUserReceiveAddress)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pUserReceiveAddressService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pUserReceiveAddress 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PUserReceiveAddress pUserReceiveAddress) {
        return success(this.pUserReceiveAddressService.save(pUserReceiveAddress));
    }

    /**
     * 修改数据
     *
     * @param pUserReceiveAddress 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PUserReceiveAddress pUserReceiveAddress) {
        return success(this.pUserReceiveAddressService.updateById(pUserReceiveAddress));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pUserReceiveAddressService.removeByIds(idList));
    }
}

