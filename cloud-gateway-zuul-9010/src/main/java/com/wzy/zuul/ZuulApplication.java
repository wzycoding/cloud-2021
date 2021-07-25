package com.wzy.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * zuul网关
 * zuul网关不需要导入SpringBoot依赖，因为zuul里面依赖了
 * spring-boot-starter
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 8:27 下午
 */
@SpringCloudApplication
@EnableFeignClients
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}