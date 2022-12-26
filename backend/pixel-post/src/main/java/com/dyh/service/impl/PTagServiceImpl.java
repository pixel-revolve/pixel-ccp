package com.dyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PTagDao;
import com.dyh.entity.PTag;
import com.dyh.service.PTagService;
import org.springframework.stereotype.Service;

/**
 * 标签(PTag)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:36:57
 */
@Service("pTagService")
public class PTagServiceImpl extends ServiceImpl<PTagDao, PTag> implements PTagService {

    /**
     * 通过标签名查询标签
     *
     * @param tag 标签
     * @return {@link R}
     */
    @Override
    public R getByTag(String tag) {
        PTag getPTag = this.baseMapper.selectOne(new QueryWrapper<PTag>()
                .select().eq("tag", tag));
        if(getPTag==null){
            return R.failed("找不到该标签");
        }
        return R.ok(getPTag);
    }
}

