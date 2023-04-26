package com.dyh.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyh.entity.POrder;
import com.dyh.entity.vo.POrderSubmitVo;
import com.dyh.exception.NoStockException;
import com.dyh.service.POrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 订单(POrder)表控制层
 *
 * @author makejava
 * @since 2023-04-01 19:05:23
 */
@RestController
@RequestMapping("/api/pOrder")
public class POrderController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private POrderService pOrderService;

    /**
     * 下单功能
     *
     * @param vo
     * @return
     */
    @PostMapping(value = "/submitOrder")
    public R submitOrder(POrderSubmitVo vo) throws ExecutionException, InterruptedException {
        try {
            R submitOrderResp = pOrderService.submitOrder(vo);
            //下单成功来到支付选择页
            //下单失败回到订单确认页重新确定订单信息
            if (submitOrderResp.getCode() == 0) {
                //成功
                return R.ok("成功");
            } else {
                String msg = "下单失败";
                switch ((int) submitOrderResp.getCode()) {
                    case 1:
                        msg += "令牌订单信息过期，请刷新再次提交";
                        break;
                    case 2:
                        msg += "订单商品价格发生变化，请确认后再次提交";
                        break;
                    case 3:
                        msg += "库存锁定失败，商品库存不足";
                        break;
                }
                pOrderService.confirmOrder();
                return R.failed(msg);
            }
        } catch (Exception e) {
            String message = "出现异常";
            if (e instanceof NoStockException) {
                message = e.getMessage();
            }
            pOrderService.confirmOrder();
            return R.failed(message);
        }
    }

    /**
     * 根据订单编号查询订单状态
     *
     * @param orderId
     * @return
     */
    @GetMapping(value = "/status/{orderId}")
    public R getOrderStatus(@PathVariable("orderId") Long orderId) {
        POrder order = pOrderService.getById(orderId);
        return R.ok(order);
    }

    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param pOrder 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<POrder> page, POrder pOrder) {
        return success(this.pOrderService.page(page, new QueryWrapper<>(pOrder)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.pOrderService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param pOrder 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody POrder pOrder) {
        return success(this.pOrderService.save(pOrder));
    }

    /**
     * 修改数据
     *
     * @param pOrder 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody POrder pOrder) {
        return success(this.pOrderService.updateById(pOrder));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.pOrderService.removeByIds(idList));
    }

}

