package com.dyh.listener;

import com.dyh.entity.POrder;
import com.dyh.service.POrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 订单关闭监听器
 *
 * @author pixel-revolve
 * @date 2023/04/05
 */
@Service
@RabbitListener(queues = "order_release_order_queue", containerFactory = "rabbitListenerContainerFactory")
public class OrderCloseListener {

    @Resource
    private POrderService orderService;

    @RabbitHandler
    public void listener(POrder pOrder, Channel channel, Message message) throws IOException {
        System.out.println("收到过期的订单信息，准备关闭订单" + pOrder.getId());
        try {
            orderService.closeOrder(pOrder);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }

}
