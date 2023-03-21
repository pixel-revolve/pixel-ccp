package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PComment;
import com.dyh.entity.vo.PCommentPostVo;
import com.dyh.service.PCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 评论(PComment)表控制层
 *
 * @author makejava
 * @since 2022-11-20 13:33:01
 */
@RestController
@RequestMapping("/api/pComment")
public class PCommentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PCommentService pCommentService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param pComment 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<PComment> page, PComment pComment) {
        return success(this.pCommentService.page(page, new QueryWrapper<>(pComment)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pCommentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pComment 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public R insert(@RequestBody PComment pComment) {
        return success(this.pCommentService.save(pComment));
    }

    /**
     * 修改数据
     *
     * @param pComment 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody PComment pComment) {
        return success(this.pCommentService.updateById(pComment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pCommentService.removeByIds(idList));
    }

    /**
     * 文章评论/发布评论
     *
     * @param pCommentPostVo p评论后签证官
     * @return {@link R}
     */
    @PostMapping("/postComment")
    public R postComment(@RequestBody PCommentPostVo pCommentPostVo){
        return this.pCommentService.postComment(pCommentPostVo);
    }

    /**
     * 评论展示
     *
     * @return {@link R}
     */
    @GetMapping("/displayComment/{postId}")
    public R displayComment(@PathVariable Long postId){
        return this.pCommentService.displayComment(postId);
    }
}

