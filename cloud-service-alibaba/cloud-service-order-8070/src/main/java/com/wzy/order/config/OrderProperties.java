package com.wzy.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/18 11:35 下午
 */
@Data
@Component
@ConfigurationProperties("order.info")
public class OrderProperties {

    /**
     * 订单最低金额
     */
    private Integer lowestPrice = 0;

    /**
     * 库存不足提示
     */
    private String underStockTips = "库存不足，请您稍后再试！";
}