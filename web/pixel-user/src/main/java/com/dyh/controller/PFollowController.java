package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PFollow;
import com.dyh.service.PFollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 关注(PFollow)表控制层
 *
 * @author makejava
 * @since 2023-02-25 22:40:17
 */
@RestController
@RequestMapping("/api/pFollow")
public class PFollowController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PFollowService pFollowService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pFollow 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<PFollow> page, PFollow pFollow) {
        return success(this.pFollowService.page(page, new QueryWrapper<>(pFollow)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pFollowService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pFollow 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody PFollow pFollow) {
        return success(this.pFollowService.save(pFollow));
    }

    /**
     * 修改数据
     *
     * @param pFollow 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody PFollow pFollow) {
        return success(this.pFollowService.updateById(pFollow));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pFollowService.removeByIds(idList));
    }

    /**
     * 关注
     *
     * @param followUserId 遵循用户id
     * @param isFollow     是遵循
     * @return {@link R}
     */
    @PutMapping("/{id}/{isFollow}")
    public R follow(@PathVariable("id") Long followUserId, @PathVariable("isFollow") Boolean isFollow) {
        return pFollowService.follow(followUserId, isFollow);
    }

    /**
     * 查看是否关注
     *
     * @param followUserId 遵循用户id
     * @return {@link R}
     */
    @GetMapping("/or/not/{id}")
    public R isFollow(@PathVariable("id") Long followUserId) {
        return pFollowService.isFollow(followUserId);
    }

    /**
     * 查看共同关注
     *
     * @param id
     * @return {@link R}
     */
    @GetMapping("/followCommons/{id}")
    public R followCommons(@PathVariable("id") Long id) {
        return  pFollowService.followCommons(id);
    }

    /**
     * 查询该id的博主的所有粉丝
     * @param id
     * @return {@link R}
     */
    @GetMapping("/queryFansById/{id}")
    public R<List<PFollow>> queryFansById(@PathVariable("id") Long id) {
        return pFollowService.queryFansById(id);
    }

    /**
     * 查询关注博主的Feed推文
     * @param max
     * @param offset
     * @return {@link R}
     */
    @GetMapping("/queryPostOfFollow/{max}/{offset}")
    public R queryPostOfFollow(@PathVariable("max") Long max,@PathVariable("offset") Integer offset){
        return pFollowService.queryPostOfFollow(max,offset);
    }

}
