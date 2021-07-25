package com.wzy.goods.facade;

import com.wzy.cloud.dto.CommonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/7/15 3:51 下午
 */
@RequestMapping("facade/goods")
@RestController
public class GoodsFacadeController {

    @Value("${server.port}")
    private String port;

    @GetMapping("info")
    public CommonResponse getInfo(@RequestParam(value = "goodsId") Long goodsId) {

        if (goodsId <= 0) {
            throw new IllegalArgumentException("商品的id不能小于等于0");
        }
        return CommonResponse.success("MacBook Pro 16英寸, 实例端口号为：" + port);
    }
}