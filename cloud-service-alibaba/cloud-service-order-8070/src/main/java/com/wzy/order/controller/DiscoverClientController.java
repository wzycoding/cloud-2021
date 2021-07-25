package com.wzy.order.controller;

import com.alibaba.fastjson.JSON;
import com.wzy.cloud.dto.CommonResponse;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/21 9:39 下午
 */
@RequestMapping("/discover/client")
@RestController
public class DiscoverClientController {

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("getAllServices")
    public CommonResponse<String> getAllServices() {
        List<String> services = discoveryClient.getServices();

        return CommonResponse.success(JSON.toJSONString(services));
    }

    @GetMapping("getAllInstances")
    public CommonResponse<String> getAllInstances(@RequestParam("serviceId") String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        return CommonResponse.success(JSON.toJSONString(instances));
    }
}