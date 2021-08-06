package com.wzy.goods;

import com.wzy.goods.datasource.DataSource;
import com.wzy.goods.datasource.DataSourceHolder;
import com.wzy.goods.datasource.InsertOperator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/8/4 5:20 下午
 */
@ComponentScan("com.wzy")
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataSourceTest {

    @Resource
    @DataSource(value = "db1")
    private InsertOperator insertOperator;

    @Test
    public void testInsert() {
        DataSourceHolder.choiceDbSource("db1");
        insertOperator.insert();

//        DataSourceHolder.choiceDbSource("db2");
        insertOperator.insert();
    }

}