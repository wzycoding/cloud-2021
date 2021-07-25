package com.wzy.order.feign.fallback;

import com.wzy.cloud.dto.CommonResponse;
import com.wzy.order.feign.GoodsFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Hystrix熔断降级service
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 5:35 下午
 */
@Component
@Slf4j
@SuppressWarnings("all")
public class GoodsFeignFallbackService implements GoodsFeignService {

    @Override
    public CommonResponse<String> getInfo(Long goodsId) {
        return CommonResponse.badRequest(10001, "系统繁忙，请稍后操作");
    }
}