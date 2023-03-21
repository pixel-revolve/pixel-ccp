package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.dao.PPostStarDao;
import com.dyh.entity.PPostStar;
import com.dyh.service.PPostStarService;
import org.springframework.stereotype.Service;

/**
 * 冒泡/文章点赞(PPostStar)表服务实现类
 *
 * @author makejava
 * @since 2022-11-20 12:36:12
 */
@Service("pPostStarService")
public class PPostStarServiceImpl extends ServiceImpl<PPostStarDao, PPostStar> implements PPostStarService {

}

