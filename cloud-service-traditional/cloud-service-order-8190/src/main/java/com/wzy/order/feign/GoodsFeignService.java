package com.wzy.order.feign;

import com.wzy.cloud.dto.CommonResponse;
import com.wzy.order.feign.fallback.GoodsFeignFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用service goods服务 feign接口
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 5:26 下午
 */
@FeignClient(name = "service-goods", fallback = GoodsFeignFallbackService.class)
public interface GoodsFeignService {
    /**
     * 获取商品信息
     *
     * @param goodsId 商品id
     * @return 商品信息
     */
    @GetMapping("facade/goods/info")
    CommonResponse<String> getInfo(@RequestParam("goodsId") Long goodsId);
}