package com.wzy.goods.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/25 6:16 下午
 */
@Data
@Builder
@AllArgsConstructor
public class OrderMsgParam {
    @Tolerate
    public OrderMsgParam() {
    }

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品id列表
     */
    private List<Long> goodsId;

    /**
     * 下单备注
     */
    private String remark;

}