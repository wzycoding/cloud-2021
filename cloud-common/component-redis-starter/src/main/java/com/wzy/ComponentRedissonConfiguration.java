package com.wzy;

/**
 * 对redisson配置进行封装，适当的简化
 *
 * @author lowi
 * @date 2021/5/13 10:04
 */
//@Slf4j
//@Configuration(proxyBeanMethods = false)
//@EnableConfigurationProperties(RedissonConfigurationProperties.class)
//@ConditionalOnProperty({"component.redisson", "component.redisson"})
//@Import(RedisClientRegister.class)
public class ComponentRedissonConfiguration {

//    private RedissonConfigurationProperties properties;
//
//    public ComponentRedissonConfiguration(RedissonConfigurationProperties properties) {
//        this.properties = properties;
//    }
//
////    @Bean
//    @ConditionalOnMissingBean(RedissonClient.class)
//    public RedissonClientFactoryBean redissonClient() {
//        RedissonClientHolder clientHolder = new RedissonClientHolder();
//        clientHolder.setProperties(properties);
//
//        return new RedissonClientFactoryBean(properties, clientHolder);
//    }

}
