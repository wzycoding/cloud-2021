package com.wzy.order.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 下订单消息通道
 *
 * @author wzy
 * @version 1.0
 * @date 2021年07月25日19:08:34
 */
public interface OrderProcessor {

    /**
     * 消息消费者配置
     */
    String GENERATE_ORDER_INPUT = "generateOrderInput";

    @Input(OrderProcessor.GENERATE_ORDER_INPUT)
    SubscribableChannel generateOrderInput();
}