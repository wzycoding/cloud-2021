package com.wzy.goods.datasource;

import lombok.Data;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/8/4 5:41 下午
 */
@Data
public class DataSourceHolder {
    private static ThreadLocal<String> datasourceName = new ThreadLocal<>();

    public static void choiceDbSource(String dbName) {
        datasourceName.set(dbName);
    }

    public static String getCurrentDB() {
        return datasourceName.get();
    }

    public static void removeDbSource() {
        datasourceName.remove();
    }
}