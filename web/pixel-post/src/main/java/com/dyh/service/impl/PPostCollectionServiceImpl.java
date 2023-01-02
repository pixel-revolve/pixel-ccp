package com.dyh.service.impl;

import cn.hutool.core.util.BooleanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PPostCollectionDao;
import com.dyh.entity.PPostCollection;
import com.dyh.service.PPostCollectionService;
import com.dyh.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.dyh.constant.RedisConstants.POST_COLLECTED_KEY;

/**
 * 冒泡/文章收藏(PPostCollection)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:36:42
 */
@Service("pPostCollectionService")
public class PPostCollectionServiceImpl extends ServiceImpl<PPostCollectionDao, PPostCollection> implements PPostCollectionService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public R collectPost(Long postId) {
        // 1.获取登录用户
        Long userId = UserHolder.getUser().getId();
        // 2.判断当前用户是否已经收藏该文章
        String key=POST_COLLECTED_KEY+userId;
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(key, userId.toString());
        if(BooleanUtil.isFalse(isMember)){
            // 3.如果未收藏
            // 3.1.如果 redis 挂掉了或者切换新的 redis 防止数据库创建重复记录
            PPostCollection getPPostCollection = this.baseMapper.selectOne(new QueryWrapper<PPostCollection>().eq("user_id", userId).eq("post_id", postId));
            if(getPPostCollection!=null){
                // 3.1.1.在数据库删除该收藏关系
                this.baseMapper.deleteById(getPPostCollection.getId());
                List<PPostCollection> postCollections = this.baseMapper.selectList(new QueryWrapper<PPostCollection>().eq("post_id", postId));
                return R.ok(postCollections.size()).setMsg("取消收藏");
            }
            PPostCollection pPostCollection = new PPostCollection();
            pPostCollection.setPostId(postId);
            pPostCollection.setUserId(userId);
            // 3.1.创建收藏关联
            boolean isSuccess = save(pPostCollection);
            if(isSuccess){
                stringRedisTemplate.opsForSet().add(key,userId.toString());
            }
        }else {
            // 4.如果已收藏
            PPostCollection getPPostCollection = this.baseMapper.selectOne(new QueryWrapper<PPostCollection>().eq("user_id", userId).eq("post_id", postId));
            if(getPPostCollection==null){
                return R.failed("未找到该收藏关系");
            }
            // 4.1.在数据库删除该收藏关系
            this.baseMapper.deleteById(getPPostCollection.getId());
            // 4.2.在redis集合中删除掉该用户
            stringRedisTemplate.opsForSet().remove(key,userId.toString());
            List<PPostCollection> postCollections = this.baseMapper.selectList(new QueryWrapper<PPostCollection>().eq("post_id", postId));
            return R.ok(postCollections.size()).setMsg("取消收藏");
        }
        List<PPostCollection> postCollections = this.baseMapper.selectList(new QueryWrapper<PPostCollection>().eq("post_id", postId));
        return R.ok(postCollections.size()).setMsg("收藏成功");
    }

    @Override
    public R selectByUserIdAndPostId(Long userId, Long postId) {
        PPostCollection getPPostCollection = this.baseMapper.selectOne(new QueryWrapper<PPostCollection>().eq("user_id", userId).eq("post_id", postId));
        if(getPPostCollection==null){
            return R.failed("未找到该收藏关系");
        }
        return R.ok(getPPostCollection);
    }
}

