package goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 商品service 启动类
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 1:54 下午
 */
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
public class Goods8091Application {
    public static void main(String[] args) {
        SpringApplication.run(Goods8091Application.class, args);
    }

}