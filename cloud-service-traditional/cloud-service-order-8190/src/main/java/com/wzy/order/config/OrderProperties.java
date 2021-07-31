package com.wzy.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/31 2:50 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "order.hello")
public class OrderProperties {

    private String desc;

}