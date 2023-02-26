package com.dyh.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyh.entity.PPost;
import com.dyh.entity.vo.PPostCreateVo;
import com.dyh.entity.vo.PPostDisplayVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 冒泡/文章(PPost)表服务接口
 *
 * @author makejava
 * @since 2022-11-20 12:35:24
 */
public interface PPostService extends IService<PPost> {

    R getById(Long id) throws JsonProcessingException;

    R pPostDisplay(Page<PPostDisplayVo> page, Wrapper<PPost> queryWrapper);

    R createPost(PPostCreateVo pPostCreateVo) throws JsonProcessingException;

    R pPostDetail(Long id);

    R likePost(Long id);

    R postLikeRank();

    R<List<PPost>> selectBatch(List<Long> idList);

}


