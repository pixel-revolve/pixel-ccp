package com.dyh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dyh.entity.PWareSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品库存(PWareSku)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@Mapper
public interface PWareSkuDao extends BaseMapper<PWareSku> {

    /**
     * 获取商品库存数量
     * @param skuId
     * @return {@link Long}
     */
    Long getSkuStock(@Param("skuId") Long skuId);


    /**
     * 将有库存的仓库列出
     * @param spuId
     * @return {@link List}<{@link Long}>
     */
    List<Long> listWareIdHasSkuStock(@Param("spuId") Long spuId);

    /**
     * 锁定库存
     * @param skuId
     * @param wareId
     * @param num
     * @return
     */
    Long lockSkuStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Integer num);

    /**
     * 解锁库存
     * @param spuId
     * @param wareId
     * @param num
     */
    void unLockStock(@Param("spuId") Long spuId, @Param("wareId") Long wareId, @Param("num") Integer num);

}