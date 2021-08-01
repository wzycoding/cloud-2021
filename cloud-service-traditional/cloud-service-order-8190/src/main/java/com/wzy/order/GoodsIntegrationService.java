package com.wzy.order;

import com.alibaba.fastjson.JSON;
import com.wzy.cloud.dto.CommonResponse;
import com.wzy.order.feign.GoodsFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品服务整合服务
 *
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 5:56 下午
 */
@Slf4j
@Service
public class GoodsIntegrationService {
    @Resource
    private GoodsFeignService goodsFeignService;

    public CommonResponse<String> getInfo(Long goodsId) {
        try {
            CommonResponse<String> resultInfo = goodsFeignService.getInfo(goodsId);

            log.info("获取商品信息远程调用结果：{}", JSON.toJSONString(resultInfo));

            return resultInfo;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);

            return CommonResponse.badRequest(10001, "系统错误");
        }
    }
}