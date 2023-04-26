package com.dyh.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.entity.vo.PCartItemVo;

import java.util.List;

public interface PCartService {

    R<List<PCartItemVo>> getUserCartItems();
}
