package com.wzy.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * #@EnableFeignClients调用方必须标注
 * #@EnableFeignClients激活feign
 * #@EnableCircuitBreaker激活断路器
 * #@EnableDiscoveryClient激活服务发现
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 2:10 下午
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class Order8190Application {
    public static void main(String[] args) {
        SpringApplication.run(Order8190Application.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}