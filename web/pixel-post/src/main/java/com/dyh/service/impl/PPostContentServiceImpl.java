package com.dyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PPostContentDao;
import com.dyh.entity.PPostContent;
import com.dyh.service.PPostContentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 冒泡/文章内容(PPostContent)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:37:18
 */
@Service("pPostContentService")
public class PPostContentServiceImpl extends ServiceImpl<PPostContentDao, PPostContent> implements PPostContentService {

    @Override
    public R<List<PPostContent>> getByPostId(Long postId) {
        List<PPostContent> contents = this.baseMapper.selectList(new QueryWrapper<PPostContent>().select().eq("post_id", postId));
        // 如果内容为空
        if(contents==null||contents.isEmpty()){
            return R.failed("无内容");
        }
        return R.ok(contents);
    }
}

