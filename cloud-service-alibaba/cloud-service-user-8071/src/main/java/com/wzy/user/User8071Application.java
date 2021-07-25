package com.wzy.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/20 12:55 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class User8071Application {
    public static void main(String[] args) {
        SpringApplication.run(User8071Application.class, args);
    }
}