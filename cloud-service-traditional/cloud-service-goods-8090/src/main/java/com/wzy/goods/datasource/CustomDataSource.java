package com.wzy.goods.datasource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/8/4 5:08 下午
 */
@Slf4j
@Data
public class CustomDataSource {
    private String url;

    private String username;

    public void insert() {
        log.info("执行了插入，url:{}, 用户名：{}", url, username);
    }
}