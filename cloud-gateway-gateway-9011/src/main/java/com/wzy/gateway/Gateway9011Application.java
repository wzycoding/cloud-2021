package com.wzy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关转发地址：http://localhost:9011/order/discover/client/getAllInstances?serviceId=user-service
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/20 1:15 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Gateway9011Application {
    public static void main(String[] args) {
        SpringApplication.run(Gateway9011Application.class, args);
    }
}