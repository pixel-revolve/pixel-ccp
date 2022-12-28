package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PPostCollection;
import com.dyh.service.PPostCollectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 冒泡/文章收藏(PPostCollection)表控制层
 *
 * @author makejava
 * @since 2022-11-20 12:36:42
 */
@RestController
@RequestMapping("/api/pPostCollection")
public class PPostCollectionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PPostCollectionService pPostCollectionService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pPostCollection 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<PPostCollection> page, PPostCollection pPostCollection) {
        return success(this.pPostCollectionService.page(page, new QueryWrapper<>(pPostCollection)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pPostCollectionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pPostCollection 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public R insert(@RequestBody PPostCollection pPostCollection) {
        return success(this.pPostCollectionService.save(pPostCollection));
    }

    /**
     * 修改数据
     *
     * @param pPostCollection 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody PPostCollection pPostCollection) {
        return success(this.pPostCollectionService.updateById(pPostCollection));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pPostCollectionService.removeByIds(idList));
    }

    /**
     * 收藏文章
     *
     * @param id id
     * @return {@link R}
     */
    @PostMapping("/collectPost/{id}")
    public R collectPost(@PathVariable Long id){
        return pPostCollectionService.collectPost(id);
    }
}

