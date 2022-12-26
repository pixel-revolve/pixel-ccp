package com.dyh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PPostDao;
import com.dyh.entity.PPost;
import com.dyh.entity.PPostContent;
import com.dyh.entity.PTag;
import com.dyh.entity.PTagPost;
import com.dyh.entity.dto.PPostContentDTO;
import com.dyh.entity.vo.PPostCreateVo;
import com.dyh.entity.vo.PPostDetailVo;
import com.dyh.entity.vo.PPostVo;
import com.dyh.feign.PUserFeignService;
import com.dyh.service.PPostContentService;
import com.dyh.service.PPostService;
import com.dyh.service.PTagPostService;
import com.dyh.service.PTagService;
import com.dyh.utils.RedisIdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.dyh.constant.PostConstants.POST_PREFIX;
import static com.dyh.constant.RedisConstants.*;

/**
 * 冒泡/文章(PPost)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:35:24
 */
@Service("pPostService")
public class PPostServiceImpl extends ServiceImpl<PPostDao, PPost> implements PPostService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    ObjectMapper objectMapper;

    @Resource
    PUserFeignService pUserFeignService;

    @Resource
    PPostContentService pPostContentService;

    @Resource
    PTagService pTagService;

    @Resource
    PTagPostService pTagPostService;

    @Resource
    RedisIdWorker redisIdWorker;

    /**
     * 由于被继承的方法返回值为泛型不好擦除于是重新构造并且实现接口方法
     *
     * @param id id
     * @return {@link R}
     */
    public R getById(Long id) throws JsonProcessingException {
        String key=CACHE_POST_KEY+id;

        // 1.从redis查询文章缓存
        String postJson= stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if(StrUtil.isNotBlank(postJson)){
            try {
                PPost pPost=objectMapper.readValue(postJson,PPost.class);
                // 3.存在，直接返回
                return R.ok(pPost);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        // 判断命中的是否是空值
        if(postJson!=null){
            // 返回一个错误信息
            R.failed("缓存错误");
        }

        // 4.不存在则根据id查询数据库
        PPost pPost= super.getById(id);
        // 5.不存在，返回错误
        if(pPost==null){
            // 将空值写入到redis
            stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
            // 返回错误信息
            return R.failed("文章不存在！");
        }
        // 6.存在，写入redis，设置过期时间
        stringRedisTemplate.opsForValue().set(key,objectMapper.writeValueAsString(pPost),CACHE_POST_TTL, TimeUnit.MINUTES);
        // 7.返回
        return R.ok(pPost);
    }

    /**
     * 文章首页显示
     *
     * @return {@link R}
     */
    @Override
    public R pPostDisplay(Page<PPostVo> page, Wrapper<PPost> queryWrapper) {
        List<PPostVo> postVos = this.baseMapper.selectPage(new Page<>(page.getCurrent(),page.getSize()), queryWrapper)
                .getRecords().stream().map((i) ->{
                    PPostVo pPostVo = BeanUtil.copyProperties(i, PPostVo.class);
                    pPostVo.setNickname(pUserFeignService.selectNicknameById(pPostVo.getUserId()).getData().toString());
                    return pPostVo;
                }).collect(Collectors.toList());

        return R.ok(page.setRecords(postVos));
    }

    @Override
    public R createPost(PPostCreateVo pPostCreateVo) throws JsonProcessingException {
        // 1.为创建的文章生成分布式ID
        long postId = redisIdWorker.nextId(POST_PREFIX);
        // 2.将vo中需要的内容取出
        List<PPostContentDTO> pPostContentDTOs=pPostCreateVo.getContents();
        List<String> tags=pPostCreateVo.getTags();
        Long userId=pPostCreateVo.getUserId();

        // 3.将文章内容存储到数据库
        pPostContentDTOs.forEach((i)->{
            PPostContent pPostContent = BeanUtil.copyProperties(i, PPostContent.class);
            pPostContent.setPostId(postId);
            pPostContent.setUserId(userId);
            // 保存文章内容到数据库
            pPostContentService.save(pPostContent);
        });

        // 4.将tag关联存储到数据库
        for (int i = 0; i < tags.size(); i++) {
            R res = pTagService.getByTag(tags.get(i));
            if(res.getCode()==ApiErrorCode.FAILED.getCode()){// 如果在查询中出错则直接返回错误信息
                return res;
            }
            // 反序列化得到PTag完整信息
            PTag getPTag=objectMapper.readValue(objectMapper.writeValueAsString(res.getData()), PTag.class);
            // 创建并且赋值需要的关联信息
            PTagPost pTagPost=new PTagPost();
            pTagPost.setPostId(postId);
            pTagPost.setTagId(getPTag.getId());
            // 存储关联信息
            pTagPostService.save(pTagPost);
        }

        PPost pPost=new PPost();
        pPost.setId(postId);
        pPost.setUserId(userId);
        pPost.setAttachmentPrice(pPostCreateVo.getAttachmentPrice());
        pPost.setSummary(pPostCreateVo.getSummary());
        pPost.setTags(listToString(tags));
        // 5.保存文章信息
        save(pPost);

        return R.ok(postId);
    }

    @Override
    public R pPostDetail(Long id) {
        // 1.通过id查询到文章
        PPost getPPost = this.baseMapper.selectById(id);
        if(getPPost==null){
            return R.failed("查询不到该文章");
        }
        // 2.提取组装文章基本信息
        PPostDetailVo pPostDetailVo = BeanUtil.copyProperties(getPPost, PPostDetailVo.class);
        // 3.获取文章内容信息
        R<List<PPostContent>> res = pPostContentService.getByPostId(id);
        if(res.getCode()==ApiErrorCode.FAILED.getCode()){// 如果获取文章内容失败
            return res;
        }
        List<PPostContent> contents = res.getData();
        // 4.将文章内容转换成DTO
        List<PPostContentDTO> contentDTOS = contents.stream().map(i -> BeanUtil.copyProperties(i, PPostContentDTO.class)).collect(Collectors.toList());
        pPostDetailVo.setContents(contentDTOS);
        // 5.返回文章细节
        return R.ok(pPostDetailVo);
    }

    @Override
    public R likePost(Long id) {
        
        return null;
    }

    private String listToString(List<String> list){
        if(list.size()==0){
            return "";
        }
        StringBuilder res=new StringBuilder();
        res.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            res.append(",");
            res.append(list.get(i));
        }
        return res.toString();
    }

}

