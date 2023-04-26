package com.dyh.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyh.constant.OrderStatusEnum;
import com.dyh.dao.POrderDao;
import com.dyh.entity.POrder;
import com.dyh.entity.POrderItem;
import com.dyh.entity.dto.PUserDTO;
import com.dyh.entity.mq.OrderCreateTo;
import com.dyh.entity.mq.OrderTo;
import com.dyh.entity.vo.*;
import com.dyh.exception.NoStockException;
import com.dyh.feign.PProductFeignService;
import com.dyh.feign.PUserFeignService;
import com.dyh.feign.PWalletFeignService;
import com.dyh.feign.PWareFeignService;
import com.dyh.service.POrderItemService;
import com.dyh.service.POrderService;
import com.dyh.utils.RedisIdWorker;
import com.dyh.utils.UserHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.dyh.constant.OrderConstants.ORDER_PREFIX;
import static com.dyh.constant.OrderConstants.USER_ORDER_TOKEN_PREFIX;
import static com.dyh.constant.RedisConstants.CART_KEY;

/**
 * 订单(POrder)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 19:05:23
 */
@Slf4j
@Service("pOrderService")
public class POrderServiceImpl extends ServiceImpl<POrderDao, POrder> implements POrderService {

    private ThreadLocal<POrderSubmitVo> confirmVoThreadLocal = new ThreadLocal<>();

    @Resource
    private RedisIdWorker idWorker;

    @Resource
    private PWareFeignService wareFeignService;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private PProductFeignService productFeignService;

    @Resource
    private PWalletFeignService walletFeignService;

    @Resource
    private PUserFeignService userFeignService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private POrderItemService orderItemService;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 订单确认页返回需要用的数据
     * @return
     */
    @Override
    public POrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {

        //构建POrderConfirmVo
        POrderConfirmVo confirmVo = new POrderConfirmVo();

        //获取当前用户登录的信息
        PUserDTO user = UserHolder.getUser();

        //TODO :获取当前线程请求头信息(解决Feign异步调用丢失请求头问题)
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        //开启第一个异步任务
        CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {

            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);

            //1、远程查询所有的收获地址列表
            List<PUserAddressVo> address = userFeignService.getAddress(user.getId());
            confirmVo.setUserAddressVos(address);
        }, threadPoolExecutor);

        //开启第二个异步任务
        CompletableFuture<Void> cartInfoFuture = CompletableFuture.runAsync(() -> {

            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);

            //2、远程查询购物车所有选中的购物项
            List<POrderItemVo> currentCartItems = walletFeignService.getCurrentCartItems().getData();
            confirmVo.setItems(currentCartItems);
            //feign在远程调用之前要构造请求，调用很多的拦截器
        }, threadPoolExecutor).thenRunAsync(() -> {
            List<POrderItemVo> items = confirmVo.getItems();
            //获取全部商品的id
            List<Long> skuIds = items.stream()
                    .map((POrderItemVo::getSkuId))
                    .collect(Collectors.toList());

            //远程查询商品库存信息
            List<PSkuHasStockVo> skuStockVos = wareFeignService.getSkuHasStock(skuIds).getData();

            if (skuStockVos != null && skuStockVos.size() > 0) {
                //将skuStockVos集合转换为map
                Map<Long, Boolean> skuHasStockMap = skuStockVos.stream().collect(Collectors.toMap(PSkuHasStockVo::getSkuId, PSkuHasStockVo::getHasStock));
                confirmVo.setStocks(skuHasStockMap);
            }
        },threadPoolExecutor);

        //3、查询用户积分

        //4、价格数据自动计算

        //TODO 5、防重令牌(防止表单重复提交)
        //为用户设置一个token，三十分钟过期时间（存在redis）
        String token = UUID.randomUUID().toString().replace("-", "");
        stringRedisTemplate.opsForValue().set(USER_ORDER_TOKEN_PREFIX+user.getId(),token,30, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);

        CompletableFuture.allOf(addressFuture,cartInfoFuture).get();

        return confirmVo;
    }


    /**
     * 提交订单
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R submitOrder(POrderSubmitVo vo) throws JsonProcessingException {

        // 设置一个订单提交项
        confirmVoThreadLocal.set(vo);

        //去创建、下订单、验令牌、验价格、锁定库存...

        //获取当前用户登录的信息
        Long userId = UserHolder.getUser().getId();

        //1、验证令牌是否合法【令牌的对比和删除必须保证原子性】
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = vo.getOrderToken();

        //toDO:通过lure脚本原子验证令牌和删除令牌
        Long result = stringRedisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class),
                List.of(USER_ORDER_TOKEN_PREFIX + userId),
                orderToken);

        if (result == 0L) {
            //令牌验证失败
            return R.failed("令牌验证失败");
        } else {
            //令牌验证成功
            //1、创建订单、订单项等信息
            OrderCreateTo order = createOrder();

            //2、验证价格
            // 创建订单时计算的应付价格
            Long payPrice = order.getPayPrice();
            // 应付总额
            Long payAmount = order.getOrder().getPayAmount();

            if (Math.abs(payAmount-payPrice) < 0.01) {
                //金额对比
                //TODO 3、保存订单
                saveOrder(order);

                //4、库存锁定,只要有异常，回滚订单数据
                //订单号、所有订单项信息(skuId,skuNum,skuName)
                PWareSkuLockVo lockVo = new PWareSkuLockVo();
                lockVo.setOrderId(order.getOrder().getId());

                //获取出要锁定的商品数据信息
                List<POrderItemVo> orderItemVos = order.getOrderItems().stream().map((item) -> {
                    POrderItemVo orderItemVo = new POrderItemVo();
                    orderItemVo.setSkuId(item.getSkuId());
                    orderItemVo.setCount(item.getSkuQuantity());
                    orderItemVo.setPic(item.getSkuPic());
                    orderItemVo.setPrice(item.getSkuPrice());
                    orderItemVo.setTitle(item.getSkuName());
                    return orderItemVo;
                }).collect(Collectors.toList());
                lockVo.setLocks(orderItemVos);

                //TODO 调用远程锁定库存的方法
                //出现的问题：扣减库存成功了，但是由于网络原因超时，出现异常，导致订单事务回滚，库存事务不回滚(解决方案：seata)
                //为了保证高并发，不推荐使用seata，因为是加锁，并行化，提升不了效率,可以发消息给库存服务
                R r = wareFeignService.orderLockStock(lockVo);
                if (r.getCode() == 0) {
                    //锁定成功
                    // int i = 10/0;

                    //TODO 订单创建成功，发送消息给MQ
                    rabbitTemplate.convertAndSend("order-event-exchange","order.create.order",order.getOrder());

                    //删除购物车里的数据
                    stringRedisTemplate.delete(CART_KEY+userId);
                    return R.ok(order.getOrder());
                } else {
                    //锁定失败
                    log.info("锁定失败");
                    throw new NoStockException(r.getMsg());
                }

            } else {
                // 金额不对
                return R.failed("金额比对有误");
            }
        }
    }

    /**
     * 关闭订单
     * @param order
     */
    @Override
    public void closeOrder(POrder order) {

        //关闭订单之前先查询一下数据库，判断此订单状态是否已支付
        POrder orderInfo = this.getById(order.getId());

        if (orderInfo.getOrderStatus().equals(OrderStatusEnum.CREATE_NEW.getCode())) {
            //代付款状态进行关单
            POrder orderUpdate = new POrder();
            orderUpdate.setId(orderInfo.getId());
            orderUpdate.setOrderStatus(OrderStatusEnum.CANCLED.getCode());
            this.updateById(orderUpdate);

            // 发送消息给MQ
            OrderTo orderTo = new OrderTo();
            BeanUtils.copyProperties(orderInfo, orderTo);

            try {
                //TODO 确保每个消息发送成功，给每个消息做好日志记录，(给数据库保存每一个详细信息)保存每个消息的详细信息
                rabbitTemplate.convertAndSend("order-event-exchange", "order.release.other", orderTo);
            } catch (Exception e) {
                //TODO 定期扫描数据库，重新发送失败的消息
            }
        }
    }

    /**
     * 保存订单所有数据
     * @param orderCreateTo
     */
    private void saveOrder(OrderCreateTo orderCreateTo) {

        //获取订单信息
        POrder order = orderCreateTo.getOrder();
        //保存订单
        this.baseMapper.insert(order);

        //获取订单项信息
        List<POrderItem> orderItems = orderCreateTo.getOrderItems();
        //批量保存订单项数据
        orderItemService.saveBatch(orderItems);
    }

    /**
     * 创建订单
     * @return {@link OrderCreateTo}
     */
    private OrderCreateTo createOrder() throws JsonProcessingException {

        OrderCreateTo createTo = new OrderCreateTo();

        //1、生成订单号
        Long orderId = idWorker.nextId(ORDER_PREFIX);
        POrder order = buildOrder(orderId);

        //2、获取到所有的订单项
        List<POrderItem> orderItems = buildOrderItems(orderId);

        //3、验价(计算价格、积分等信息)
        computePrice(order,orderItems);

        createTo.setOrder(order);
        createTo.setOrderItems(orderItems);

        return createTo;
    }

    /**
     * 计算价格的方法
     * @param order
     * @param orderItemEntities
     */
    private void computePrice(POrder order, List<POrderItem> orderItemEntities) {
        //总价
        Long total = 0L;
        //订单总额，叠加每一个订单项的总额信息
        for (POrderItem orderItem : orderItemEntities) {
            //总价
            total+=orderItem.getSkuPrice();
        }
        //1、订单价格相关的
        order.setTotalAmount(total);
        //设置应付总额(总额+运费)
        order.setPayAmount(total+order.getFreightAmount());
        //设置删除状态(0-未删除，1-已删除)
        order.setIsDel(0);

    }

    /**
     * 构建订单数据
     * @param orderId
     * @return
     */
    private POrder buildOrder(Long orderId) throws JsonProcessingException {

        //获取当前用户登录信息
        PUserDTO user = UserHolder.getUser();

        //开始构建订单
        POrder order = new POrder();
        order.setUserId(user.getId());
        order.setId(orderId);

        POrderSubmitVo POrderSubmitVo = confirmVoThreadLocal.get();

        //远程获取收货地址和运费信息
        R fareAddressVo = wareFeignService.getFare(POrderSubmitVo.getAddrId());
        PFareVo pFareVo = objectMapper.readValue(objectMapper.writeValueAsString(fareAddressVo.getData()), PFareVo.class);

        //获取到运费信息
        Long fare = pFareVo.getFare();
        order.setFreightAmount(fare);

        //获取到收货地址信息
        PUserAddressVo address = pFareVo.getAddress();
        //设置收货人信息
        order.setReceiverName(address.getName());
        order.setReceiverPhone(address.getPhone());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverCity(address.getCity());
        order.setReceiverDetailAddress(address.getDetailAddress());

        //设置订单相关的状态信息
        order.setOrderStatus(OrderStatusEnum.CREATE_NEW.getCode());
        return order;
    }

    /**
     * 构建所有订单项数据
     * @return
     */
    public List<POrderItem> buildOrderItems(Long orderId) {

        List<POrderItem> orderItemEntityList = new ArrayList<>();

        //最后确定每个购物项的价格
        List<POrderItemVo> currentCartItems = walletFeignService.getCurrentCartItems().getData();
        if (currentCartItems != null && currentCartItems.size() > 0) {
            orderItemEntityList = currentCartItems.stream().map((items) -> {
                //构建订单项数据
                POrderItem orderItem = null;
                try {
                    orderItem = buildOrderItem(items);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                assert orderItem != null;
                orderItem.setOrderId(orderId);

                return orderItem;
            }).collect(Collectors.toList());
        }

        return orderItemEntityList;
    }

    /**
     * 构建某一个订单项的数据
     * @param items
     * @return
     */
    private POrderItem buildOrderItem(POrderItemVo items) throws JsonProcessingException {

        //1、商品的spu信息
        Long skuId = items.getSkuId();
        //获取spu的信息
        R spuInfo = productFeignService.selectSpuInfoBySkuId(skuId);
        PSpuInfoVo pSpuInfoVo = objectMapper.readValue(objectMapper.writeValueAsString(spuInfo.getData()), PSpuInfoVo.class);

        return POrderItem.builder()
                .spuId(pSpuInfoVo.getId())
                .spuName(pSpuInfoVo.getSpuName())
                .categoryId(pSpuInfoVo.getCatalogId())
                .skuQuantity(items.getCount())
                // 这里没有做什么优惠处理
                .skuPrice(pSpuInfoVo.getPrice()*items.getCount())
                .build();
    }



}

