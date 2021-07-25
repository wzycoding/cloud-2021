package com.wzy.order.controller;

import com.wzy.cloud.dto.CommonResponse;
import com.wzy.order.config.OrderProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试读取配置
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/18 11:39 下午
 */
@Slf4j
@RestController
@RequestMapping("test/config")
public class TestConfigController {

    @Resource
    private OrderProperties orderProperties;

    /**
     * 获取库存不足提示
     * 本地地址：localhost:8070/test/config/getUnderStockTips
     */
    @GetMapping("/getUnderStockTips")
    public CommonResponse<String> getUnderStockTips() {
        return CommonResponse.success(orderProperties.getUnderStockTips());
    }

}