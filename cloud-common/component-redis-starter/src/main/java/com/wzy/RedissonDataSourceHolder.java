package com.wzy;

/**
 * redisson当前线程选择哪个数据源Holder
 *
 * @author wzy
 * @version 1.0
 * @date 2021/8/5 2:42 下午
 */
public class RedissonDataSourceHolder {

    private RedissonDataSourceHolder() {

    }

    /**
     * 当前数据源TheadLocal
     */
    private static ThreadLocal<String> currentDataSource = new ThreadLocal<>();

    /**
     * 选择数据源
     *
     * @param dataSourceName 数据源名称
     */
    public static void choiceDataSource(String dataSourceName) {
        currentDataSource.set(dataSourceName);
    }

    /**
     * 获取当前数据源名称
     *
     * @return 数据源名称
     */
    public static String getCurrentDataSource() {
        return currentDataSource.get();
    }

    /**
     * 移除数据源名称
     */
    public static void removeDataSource() {
        currentDataSource.remove();
    }
}