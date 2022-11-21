package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PCommentContent;
import com.dyh.service.PCommentContentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 评论内容(PCommentContent)表控制层
 *
 * @author makejava
 * @since 2022-11-20 13:33:14
 */
@RestController
@RequestMapping("/api/pCommentContent")
public class PCommentContentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PCommentContentService pCommentContentService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pCommentContent 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PCommentContent> page, PCommentContent pCommentContent) {
        return success(this.pCommentContentService.page(page, new QueryWrapper<>(pCommentContent)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pCommentContentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pCommentContent 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PCommentContent pCommentContent) {
        return success(this.pCommentContentService.save(pCommentContent));
    }

    /**
     * 修改数据
     *
     * @param pCommentContent 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PCommentContent pCommentContent) {
        return success(this.pCommentContentService.updateById(pCommentContent));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pCommentContentService.removeByIds(idList));
    }
}

