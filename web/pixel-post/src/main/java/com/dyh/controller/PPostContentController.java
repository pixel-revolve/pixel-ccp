package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PPostContent;
import com.dyh.service.PPostContentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 冒泡/文章内容(PPostContent)表控制层
 *
 * @author makejava
 * @since 2022-11-20 12:37:18
 */
@RestController
@RequestMapping("/api/pPostContent")
public class PPostContentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PPostContentService pPostContentService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pPostContent 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<PPostContent> page, PPostContent pPostContent) {
        return success(this.pPostContentService.page(page, new QueryWrapper<>(pPostContent)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pPostContentService.getById(id));
    }

    /**
     * 通过文章id查询文章对应内容
     *
     * @param postId 主键
     * @return 单条数据
     */
    @GetMapping("/selectByPostId/{postId}")
    public R selectByPostId(@PathVariable Long postId) {
        return this.pPostContentService.getByPostId(postId);
    }


    /**
     * 新增数据
     *
     * @param pPostContent 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public R insert(@RequestBody PPostContent pPostContent) {
        return success(this.pPostContentService.save(pPostContent));
    }

    /**
     * 修改数据
     *
     * @param pPostContent 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody PPostContent pPostContent) {
        return success(this.pPostContentService.updateById(pPostContent));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pPostContentService.removeByIds(idList));
    }
}

