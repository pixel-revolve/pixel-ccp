package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PPostStar;
import com.dyh.service.PPostStarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 冒泡/文章点赞(PPostStar)表控制层
 *
 * @author makejava
 * @since 2022-11-20 12:36:12
 */
@RestController
@RequestMapping("/api/pPostStar")
public class PPostStarController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PPostStarService pPostStarService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pPostStar 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PPostStar> page, PPostStar pPostStar) {
        return success(this.pPostStarService.page(page, new QueryWrapper<>(pPostStar)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pPostStarService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pPostStar 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PPostStar pPostStar) {
        return success(this.pPostStarService.save(pPostStar));
    }

    /**
     * 修改数据
     *
     * @param pPostStar 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PPostStar pPostStar) {
        return success(this.pPostStarService.updateById(pPostStar));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pPostStarService.removeByIds(idList));
    }
}

