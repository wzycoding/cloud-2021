package com.wzy.goods.msg.impl;

import com.alibaba.fastjson.JSON;
import com.wzy.goods.bo.OrderMsgBO;
import com.wzy.goods.msg.OrderMsgService;
import com.wzy.goods.msg.OrderProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * 消息发送服务
 * <p>
 * #@EnableBinding(Source.class)消息发送管道定义
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/25 6:16 下午
 */
@Slf4j
@EnableBinding({OrderProcessor.class})
@Service
@AllArgsConstructor
public class OrderMsgServiceImpl implements OrderMsgService {

    @Qualifier(value = "generateOrderOutput")
    private final MessageChannel generateOrderOutput;

    @Override
    public void sendOrderMsg(OrderMsgBO orderMsgBO) {

        generateOrderOutput.send(MessageBuilder.withPayload(orderMsgBO).build());

        log.info("下订单信息:{}", JSON.toJSONString(orderMsgBO));
    }
}