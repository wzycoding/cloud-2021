package com.wzy.goods;

import com.wzy.goods.datasource.DataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * 商品service 启动类
 * <p>
 * #@EnableFeignClients调用方必须标注
 * #@EnableFeignClients激活feign
 * #@EnableCircuitBreaker激活断路器
 * #@EnableDiscoveryClient激活服务发现
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 1:54 下午
 */
@Import(DataSourceRegister.class)
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
public class Goods8090Application {
    public static void main(String[] args) {
        SpringApplication.run(Goods8090Application.class, args);
    }
}