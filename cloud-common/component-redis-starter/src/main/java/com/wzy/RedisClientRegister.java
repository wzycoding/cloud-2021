package com.wzy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 手工注入多个配置文件中定义的
 *
 * @author wzy
 * @version 1.0
 * @date 2021/8/5 2:46 下午
 */
@Slf4j
public class RedisClientRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    /**
     * 参数绑定工具 springboot2.0新推出
     */
    private Binder binder;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        RedissonConfigurationProperties redissonConfigurationProperties =
                binder.bind("component.redisson", RedissonConfigurationProperties.class).get();

        RedissonConfigurationProperties configurationMetaInfos =
                redissonConfigurationProperties;

        Map<String, RedissonMetaInfo> configs = configurationMetaInfos.getConfigs();

        //如果没有默认的配置直接让项目启动不起来
        Assert.notNull(configs.get("default"), "未配置Redisson默认数据源，请检查配置！");

        RedissonClientBeanDefinitionHolder redissonClientBeanDefinitionHolder = new RedissonClientBeanDefinitionHolder();

        //循环遍历配置初始化Redisson数据源
        configs.forEach(
                (dataSourceName, metaInfo) ->
                        registry.registerBeanDefinition(dataSourceName + "RedissonDataSource",
                                redissonClientBeanDefinitionHolder.get(dataSourceName, metaInfo)));
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        log.info("开始注册数据源");
        // 绑定配置器
        binder = Binder.get(environment);
    }
}