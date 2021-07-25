package com.wzy.order.listener;

import com.alibaba.fastjson.JSON;
import com.wzy.order.bo.OrderMsgBO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/25 6:38 下午
 */
@Slf4j
@Component
@EnableBinding(OrderProcessor.class)
@AllArgsConstructor
public class OrderMsgListenerService {

    @StreamListener(OrderProcessor.GENERATE_ORDER_INPUT)
    public void handleOrderInfo(OrderMsgBO orderMsgBO) {
        log.info("监听到消息队列里面的下单信息： {}", JSON.toJSONString(orderMsgBO));
    }
}