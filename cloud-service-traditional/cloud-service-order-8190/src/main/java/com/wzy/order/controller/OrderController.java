package com.wzy.order.controller;

import com.alibaba.fastjson.JSON;
import com.wzy.cloud.dto.CommonResponse;
import com.wzy.order.GoodsIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Order Controller
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 4:05 下午
 */
@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private GoodsIntegrationService goodsIntegrationService;

    @GetMapping("/getGoodsInfo")
    public CommonResponse<String> getGoodsInfo(@RequestParam(value = "goodsId") Long goodsId) {
//        //直接使用url地址进行访问
//        callByDirectUrl(goodsId);
//        //使用ribbon进行调用
//        callRibbonUrl(goodsId);

        //使用openfeign
        return callByFeign(goodsId);
    }

    /**
     * 使用直接调用url方式
     *
     * @param goodsId 商品id
     * @return 响应结果
     */
    private CommonResponse<String> callByDirectUrl(Long goodsId) {
        ResponseEntity<CommonResponse> resultEntity = restTemplate.getForEntity("http://localhost:8090/facade/goods/info", CommonResponse.class);
        log.info(JSON.toJSONString(resultEntity.getBody()));
        return resultEntity.getBody();
    }

    /**
     * 是否用ribbon负载均衡，通过服务名进行调用
     *
     * @param goodsId 商品id
     * @return 响应结果
     */
    private CommonResponse<String> callRibbonUrl(Long goodsId) {
        ResponseEntity<CommonResponse> resultEntity = restTemplate.getForEntity("http://service-goods/facade/goods/info", CommonResponse.class);
        log.info(JSON.toJSONString(resultEntity.getBody()));
        return resultEntity.getBody();
    }

    /**
     * 通过feign进行调用
     *
     * @param goodsId 商品id
     * @return 响应结果
     */
    private CommonResponse<String> callByFeign(Long goodsId) {
        return goodsIntegrationService.getInfo(goodsId);
    }
}