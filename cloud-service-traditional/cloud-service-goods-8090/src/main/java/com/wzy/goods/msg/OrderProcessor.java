package com.wzy.goods.msg;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 下订单消息通道
 *
 * @author wzy
 * @version 1.0
 * @date 2021年07月25日19:08:34
 */
public interface OrderProcessor {

    /**
     * 消费生产者的配置
     */
    String GENERATE_ORDER_OUTPUT = "generateOrderOutput";

    @Output(OrderProcessor.GENERATE_ORDER_OUTPUT)
    MessageChannel generateOrderOutput();
}