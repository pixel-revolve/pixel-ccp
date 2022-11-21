package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PAttachment;
import com.dyh.service.PAttachmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 附件(PAttachment)表控制层
 *
 * @author makejava
 * @since 2022-11-20 12:37:41
 */
@RestController
@RequestMapping("/api/pAttachment")
public class PAttachmentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PAttachmentService pAttachmentService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pAttachment 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PAttachment> page, PAttachment pAttachment) {
        return success(this.pAttachmentService.page(page, new QueryWrapper<>(pAttachment)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pAttachmentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pAttachment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PAttachment pAttachment) {
        return success(this.pAttachmentService.save(pAttachment));
    }

    /**
     * 修改数据
     *
     * @param pAttachment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PAttachment pAttachment) {
        return success(this.pAttachmentService.updateById(pAttachment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pAttachmentService.removeByIds(idList));
    }
}

