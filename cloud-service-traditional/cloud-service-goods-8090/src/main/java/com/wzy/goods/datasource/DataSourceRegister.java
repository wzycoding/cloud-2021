package com.wzy.goods.datasource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wzy
 * @version 1.0
 * @date 2021/8/4 5:09 下午
 */
public class DataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        GenericBeanDefinition beanDefinition1 = new GenericBeanDefinition();

        beanDefinition1.setBeanClass(CustomDataSource.class);
        // 需要注入的参数
        MutablePropertyValues mpv1 = beanDefinition1.getPropertyValues();
        // 添加默认数据源，避免key不存在的情况没有数据源可用
        mpv1.add("url", "db1:url");
        // 添加其他数据源
        mpv1.add("username", "db1");


        GenericBeanDefinition beanDefinition2 = new GenericBeanDefinition();

        beanDefinition2.setBeanClass(CustomDataSource.class);
        // 需要注入的参数
        MutablePropertyValues mpv2 = beanDefinition2.getPropertyValues();
        // 添加默认数据源，避免key不存在的情况没有数据源可用
        mpv2.add("url", "db2:url");
        // 添加其他数据源
        mpv2.add("username", "db2");

        GenericBeanDefinition beanDefinition3 = new GenericBeanDefinition();

        beanDefinition3.setBeanClass(CustomDataSource.class);
        // 需要注入的参数
        MutablePropertyValues mpv3 = beanDefinition3.getPropertyValues();
        // 添加默认数据源，避免key不存在的情况没有数据源可用
        mpv3.add("url", "default:url");
        // 添加其他数据源
        mpv3.add("username", "default");

        registry.registerBeanDefinition("db1", beanDefinition1);
        registry.registerBeanDefinition("db2", beanDefinition2);
        registry.registerBeanDefinition("default", beanDefinition3);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}