package com.wzy.goods.controller;

import com.wzy.cloud.dto.CommonResponse;
import com.wzy.goods.bo.OrderMsgBO;
import com.wzy.goods.msg.OrderMsgService;
import com.wzy.goods.param.OrderMsgParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/25 6:32 下午
 */
@RestController
@RequestMapping("v1/goods")
@AllArgsConstructor
public class GoodsController {

    private final OrderMsgService orderMsgService;

    /**
     * 下订单接口
     */
    @PostMapping("placeOrder")
    public CommonResponse placeOrder(@RequestBody OrderMsgParam param) {

        OrderMsgBO orderMsgBO = new OrderMsgBO();
        BeanUtils.copyProperties(param, orderMsgBO);

        orderMsgService.sendOrderMsg(orderMsgBO);

        return CommonResponse.success();
    }
}