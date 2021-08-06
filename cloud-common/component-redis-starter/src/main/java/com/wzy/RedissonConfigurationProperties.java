package com.wzy;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;

/**
 * @author lowi
 * @date 2021/5/24 17:06
 */
@Import(RedisClientRegister.class)
@Data
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "component.redisson")
@ConditionalOnProperty({"component.redisson", "component.redisson"})
public class RedissonConfigurationProperties {

    /**
     * Redisson多数据源配置
     */
    private Map<String, RedissonMetaInfo> configs;
}
