package com.dyh.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.PCommentReply;
import com.dyh.entity.vo.PCommentReplyVo;
import com.dyh.service.PCommentReplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 评论回复(PCommentReply)表控制层
 *
 * @author makejava
 * @since 2022-11-20 13:33:24
 */
@RestController
@RequestMapping("/api/pCommentReply")
public class PCommentReplyController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PCommentReplyService pCommentReplyService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param pCommentReply 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<PCommentReply> page, PCommentReply pCommentReply) {
        return success(this.pCommentReplyService.page(page, new QueryWrapper<>(pCommentReply)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pCommentReplyService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pCommentReply 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public R insert(@RequestBody PCommentReply pCommentReply) {
        return success(this.pCommentReplyService.save(pCommentReply));
    }

    /**
     * 修改数据
     *
     * @param pCommentReply 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody PCommentReply pCommentReply) {
        return success(this.pCommentReplyService.updateById(pCommentReply));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pCommentReplyService.removeByIds(idList));
    }

    @PostMapping("/replyComment")
    public R replyComment(@RequestBody PCommentReplyVo pCommentReplyVo){
        return this.pCommentReplyService.replyComment(pCommentReplyVo);
    }
}
