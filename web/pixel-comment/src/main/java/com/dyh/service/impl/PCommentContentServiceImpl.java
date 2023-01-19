package com.dyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PCommentContentDao;
import com.dyh.entity.PCommentContent;
import com.dyh.service.PCommentContentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论内容(PCommentContent)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 13:33:14
 */
@Service("pCommentContentService")
public class PCommentContentServiceImpl extends ServiceImpl<PCommentContentDao, PCommentContent> implements PCommentContentService {

    @Override
    public R<List<PCommentContent>> getByCommentId(Long commentId) {
        List<PCommentContent> contents = this.baseMapper.selectList(new QueryWrapper<PCommentContent>().select().eq("comment_id", commentId));
        // 如果内容为空
        if (contents == null || contents.isEmpty()) {
            return R.failed("无内容");
        }
        return R.ok(contents);
    }
}

