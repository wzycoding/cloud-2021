package com.wzy.goods.msg;

import com.wzy.goods.bo.OrderMsgBO;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/25 6:15 下午
 */
public interface OrderMsgService {

    /**
     * 发送下单消息
     *
     * @param orderMsgBO 下单消息bo
     */
    void sendOrderMsg(OrderMsgBO orderMsgBO);
}