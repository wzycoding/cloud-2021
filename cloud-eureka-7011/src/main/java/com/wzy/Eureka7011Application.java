package com.wzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心集群2
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 2:49 下午
 */
@EnableEurekaServer
@SpringBootApplication
public class Eureka7011Application {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7011Application.class, args);
    }
}