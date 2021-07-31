package com.wzy.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/31 10:20 上午
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class CloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }
}