package com.wzy.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/21 11:34 下午
 */
@Slf4j
@Component
public class LogPostGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        Long startTime = (Long) exchange.getAttributes().get("startTime");

        long endTime = System.currentTimeMillis();
        ServerHttpRequest request = exchange.getRequest();

        log.info("请求路径：{}, 请求类型：{}, 执行时间：{}", request.getURI(), request.getMethod(), endTime - startTime);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}