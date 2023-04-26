package com.dyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.constant.OrderStatusEnum;
import com.dyh.constant.WareOrderTaskDetailLockStatusEnum;
import com.dyh.dao.PWareSkuDao;
import com.dyh.entity.PWareOrderTask;
import com.dyh.entity.PWareOrderTaskDetail;
import com.dyh.entity.PWareSku;
import com.dyh.entity.mq.OrderTo;
import com.dyh.entity.mq.StockDetailTo;
import com.dyh.entity.mq.StockLockedTo;
import com.dyh.entity.po.POrder;
import com.dyh.entity.vo.POrderItemVo;
import com.dyh.entity.vo.PSkuHasStockVo;
import com.dyh.entity.vo.PWareSkuLockVo;
import com.dyh.exception.NoStockException;
import com.dyh.feign.OrderFeignService;
import com.dyh.service.PWareOrderTaskDetailService;
import com.dyh.service.PWareOrderTaskService;
import com.dyh.service.PWareSkuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 产品库存(PWareSku)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 19:08:05
 */
@Service("pWareSkuService")
public class PWareSkuServiceImpl extends ServiceImpl<PWareSkuDao, PWareSku> implements PWareSkuService {

    @Resource
    PWareOrderTaskService wareOrderTaskService;

    @Resource
    PWareOrderTaskDetailService wareOrderTaskDetailService;

    @Resource
    OrderFeignService orderFeignService;

    @Resource
    ObjectMapper objectMapper;

    @Resource
    PWareSkuDao wareSkuDao;

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 解锁库存
     *
     * @param to 来
     */
    @Override
    public void unlockStock(StockLockedTo to) {
        //库存工作单细节的id
        StockDetailTo detail = to.getDetailTo();
        Long detailId = detail.getId();
        /*
         * 解锁
         * 1、查询数据库关于这个订单锁定库存信息
         *   有：证明库存锁定成功了
         *      解锁：订单状况
         *          1、没有这个订单，必须解锁库存
         *          2、有这个订单，不一定解锁库存
         *              订单状态：已取消：解锁库存
         *                      已支付：不能解锁库存
         */
        PWareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.getById(detailId);

        if (wareOrderTaskDetail != null) {
            //查出ware_order_task工作单的信息
            Long orderTaskId = to.getId();
            PWareOrderTask wareOrderTask = wareOrderTaskService.getById(orderTaskId);
            //获取订单号查询订单状态
            Long orderId = wareOrderTask.getOrderId();
            //远程查询订单信息
            R orderData = orderFeignService.getOrderStatus(orderId);
            if (orderData.getCode() != ApiErrorCode.FAILED.getCode()) {
                //订单数据返回成功
                POrder orderInfo = null;
                try {
                    orderInfo = objectMapper.readValue(objectMapper.writeValueAsString(orderData.getData()), POrder.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                //判断订单状态是否已取消或者支付或者订单不存在
                if (orderInfo == null || Objects.equals(orderInfo.getOrderStatus(), OrderStatusEnum.CANCLED.getCode())) {
                    //订单已被取消，才能解锁库存
                    if (Objects.equals(wareOrderTaskDetail.getLockStatus(), WareOrderTaskDetailLockStatusEnum.LOCKED.getCode())) {
                        //当前库存工作单详情状态1，已锁定，但是未解锁才可以解锁
                        unLockStock(wareOrderTaskDetail.getSkuId(), wareOrderTaskDetail.getWareId(), wareOrderTaskDetail.getSkuNum(), detailId);
                    }
                }
            } else {
                //消息拒绝以后重新放在队列里面，让别人继续消费解锁
                //远程调用服务失败
                throw new RuntimeException("远程调用服务失败");
            }
        } else {
            //无需解锁
        }
    }

    /**
     * 防止订单服务卡顿，导致订单状态消息一直改不了，库存优先到期，查订单状态新建，什么都不处理
     * 导致卡顿的订单，永远都不能解锁库存
     *
     * @param orderTo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unlockStock(OrderTo orderTo) {

        Long orderId=orderTo.getId();

        //查一下最新的库存解锁状态，防止重复解锁库存
        PWareOrderTask orderTask = wareOrderTaskService.getById(orderId);

        //按照工作单的id找到所有 没有解锁的库存，进行解锁
        Long id = orderTask.getId();
        List<PWareOrderTaskDetail> list = wareOrderTaskDetailService.list(new QueryWrapper<PWareOrderTaskDetail>()
                .eq("task_id", id).eq("lock_status", WareOrderTaskDetailLockStatusEnum.LOCKED.getCode()));

        for (PWareOrderTaskDetail orderTaskDetail : list) {
            unLockStock(orderTaskDetail.getSkuId(),
                    orderTaskDetail.getWareId(),
                    orderTaskDetail.getSkuNum(),
                    orderTaskDetail.getId());
        }

    }

    /**
     * 为某个订单锁定库存
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R orderLockStock(PWareSkuLockVo vo) {
        /*
         * 保存库存工作单详情信息
         * 追溯
         */
        PWareOrderTask wareOrderTask = new PWareOrderTask();
        wareOrderTask.setOrderId(vo.getOrderId());
        wareOrderTaskService.save(wareOrderTask);

        //1、按照下单的收货地址，找到一个就近仓库，锁定库存
        //2、找到每个商品在哪个仓库都有库存
        List<POrderItemVo> locks = vo.getLocks();

        List<SkuWareHasStock> skuWareHasStocks = locks.stream().map((item) -> {
            SkuWareHasStock stock = new SkuWareHasStock();
            Long skuId = item.getSkuId();
            stock.setNum(item.getCount());
            //查询这个商品在哪个仓库有库存
            List<Long> wareIdList = wareSkuDao.listWareIdHasSkuStock(skuId);
            stock.setWareId(wareIdList);
            return stock;
        }).collect(Collectors.toList());

        //2、锁定库存
        for (SkuWareHasStock hasStock : skuWareHasStocks) {
            boolean skuStocked = false;
            Long skuId = hasStock.getSkuId();
            List<Long> wareIds = hasStock.getWareId();

            if (StringUtils.isEmpty(wareIds)) {
                //没有任何仓库有这个商品的库存
                throw new NoStockException(skuId);
            }

            //1、如果每一个商品都锁定成功,将当前商品锁定了几件的工作单记录发给MQ
            //2、锁定失败。前面保存的工作单信息都回滚了。发送出去的消息，即使要解锁库存，由于在数据库查不到指定的id，所有就不用解锁
            for (Long wareId : wareIds) {
                //锁定成功就返回1，失败就返回0
                Long count = wareSkuDao.lockSkuStock(skuId,wareId,hasStock.getNum());
                if (count == 1) {
                    skuStocked = true;
                    PWareOrderTaskDetail wareOrderTaskDetail = PWareOrderTaskDetail.builder()
                            .skuId(skuId)
                            .skuNum(hasStock.getNum())
                            .taskId(wareOrderTask.getId())
                            .wareId(wareId)
                            .lockStatus(1)
                            .build();
                    wareOrderTaskDetailService.save(wareOrderTaskDetail);

                    //TODO 告诉MQ库存锁定成功
                    StockLockedTo lockedTo = new StockLockedTo();
                    lockedTo.setId(wareOrderTask.getId());
                    StockDetailTo detailTo = new StockDetailTo();
                    BeanUtils.copyProperties(wareOrderTaskDetail,detailTo);
                    lockedTo.setDetailTo(detailTo);
                    rabbitTemplate.convertAndSend("stock-event-exchange","stock.locked",lockedTo);
                    break;
                } else {
                    //当前仓库锁失败，重试下一个仓库
                }
            }

            if (!skuStocked) {
                //当前商品所有仓库都没有锁住
                throw new NoStockException(skuId);
            }
        }

        //3、肯定全部都是锁定成功的
        return R.ok(true);
    }

    /**
     * 得到库存数量和是否有库存
     *
     * @param skuIds
     * @return {@link R}
     */
    @Override
    public R<List<PSkuHasStockVo>> getSkuHasStock(List<Long> skuIds) {
        List<PSkuHasStockVo> skuHasStockVos = skuIds.stream().map(item -> {
            Long count = this.baseMapper.getSkuStock(item);
            PSkuHasStockVo skuHasStockVo = new PSkuHasStockVo();
            skuHasStockVo.setSkuId(item);
            skuHasStockVo.setHasStock(count != null && count > 0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return R.ok(skuHasStockVos);
    }

    /**
     * 解锁库存的方法
     *
     * @param skuId
     * @param wareId
     * @param num
     * @param taskDetailId
     */
    public void unLockStock(Long skuId, Long wareId, Integer num, Long taskDetailId) {
        //库存解锁
        wareSkuDao.unLockStock(skuId, wareId, num);

        //更新工作单的状态
        PWareOrderTaskDetail wareOrderTaskDetail = new PWareOrderTaskDetail();
        wareOrderTaskDetail.setId(taskDetailId);
        //变为已解锁
        wareOrderTaskDetail.setLockStatus(WareOrderTaskDetailLockStatusEnum.UNLOCKED.getCode());
        wareOrderTaskDetailService.updateById(wareOrderTaskDetail);
    }

    /**
     * 商品库存对应仓库的库存
     *
     * @author pixel-revolve
     * @date 2023/04/17
     */
    @Data
    static class SkuWareHasStock {
        private Long skuId;
        private Integer num;
        private List<Long> wareId;
    }

}

