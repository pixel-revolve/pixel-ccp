package com.dyh.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.dyh.config.FeignConfig;
import com.dyh.entity.PPost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "pixel-post",configuration = FeignConfig.class)
public interface PPostFeignService {
    String UNIFORM_PREFIX="/api/pPost";

    @GetMapping(UNIFORM_PREFIX+"/selectBatch")
    R<List<PPost>> selectBatch(@RequestParam("idList") List<Long> idList);
}
