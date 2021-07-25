package com.wzy.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/20 12:46 下午
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Storage8072Application {
    public static void main(String[] args) {
        SpringApplication.run(Storage8072Application.class, args);
    }
}