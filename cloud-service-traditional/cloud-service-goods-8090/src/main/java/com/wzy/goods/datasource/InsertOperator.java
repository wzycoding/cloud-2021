package com.wzy.goods.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/8/4 5:15 下午
 */
@Slf4j
@Component
public class InsertOperator {
    @Autowired
    private ApplicationContext applicationContext;

    private static ApplicationContext staticApplicationContext;

    @PostConstruct
    public void init() {
        InsertOperator.staticApplicationContext = applicationContext;
    }

    public static CustomDataSource getDbSource() {
        String dbName = DataSourceHolder.getCurrentDB();
        if (StringUtils.isEmpty(dbName)) {
            //使用默认的
            return staticApplicationContext.getBean("default", CustomDataSource.class);
        }

        CustomDataSource bean = staticApplicationContext.getBean(dbName, CustomDataSource.class);

        if (Objects.isNull(bean)) {
            //使用默认的
            bean = staticApplicationContext.getBean("default", CustomDataSource.class);
        }

        DataSourceHolder.removeDbSource();

        return bean;
    }

    public void insert() {
        log.info("====调用插入前====");
        getDbSource().insert();
        log.info("====调用插入后====");
    }
}