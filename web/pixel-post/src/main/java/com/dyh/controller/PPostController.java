package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PPost;
import com.dyh.entity.vo.PPostCreateVo;
import com.dyh.entity.vo.PPostVo;
import com.dyh.service.PPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

import static com.dyh.constant.RedisConstants.CACHE_POST_KEY;

/**
 * 冒泡/文章(PPost)表控制层
 *
 * @author makejava
 * @since 2022-11-20 12:35:24
 */
@RestController
@RequestMapping("/api/pPost")
public class PPostController extends ApiController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 服务对象
     */
    @Resource
    private PPostService pPostService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pPost 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<PPost> page, PPost pPost) {
        return success(this.pPostService.page(page, new QueryWrapper<>(pPost)));
    }

    /**
     * 文章首页展示
     *
     * @param page 分页对象
     * @param pPost 查询实体
     * @return 所有数据
     */
    @GetMapping("/pPostDisplay")
    public R pPostDisplay(Page<PPostVo> page, PPost pPost) {
        return success(this.pPostService.pPostDisplay(page, new QueryWrapper<>(pPost)));
    }

    /**
     * modified
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public R selectOne(@PathVariable Long id) throws JsonProcessingException {
        return this.pPostService.getById(id);
    }

    /**
     * 通过主键查询文章详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/pPostDetail/{id}")
    public R pPostDetail(@PathVariable Long id) throws JsonProcessingException {
        return this.pPostService.pPostDetail(id);
    }

    /**
     * 新增数据
     *
     * @param pPost 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public R insert(@RequestBody PPost pPost) {
        return success(this.pPostService.save(pPost));
    }


    /**
     * 创作文章
     *
     * @param pPostCreateVo p创建后签证官
     * @return {@link R}
     * @throws JsonProcessingException json处理异常
     */
    @PostMapping("/createPost")
    public R createPost(@RequestBody PPostCreateVo pPostCreateVo) throws JsonProcessingException {
        return this.pPostService.createPost(pPostCreateVo);
    }

    /**
     * modified
     * 修改数据
     *
     * @param pPost 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody PPost pPost) {
        Long id=pPost.getId();
        if(id==null){
            return R.failed("文章id不能为空");
        }
        // 1.更新数据库
        this.pPostService.updateById(pPost);
        // 2.删除缓存
        stringRedisTemplate.delete(CACHE_POST_KEY+id);
        return R.ok("更新成功！");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pPostService.removeByIds(idList));
    }


    @PostMapping("/likePost/{id}")
    public R likePost(@PathVariable Long id){
        return pPostService.likePost(id);
    }
}

