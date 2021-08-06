package com.wzy;

import cn.hutool.core.util.ClassLoaderUtil;
import com.bxm.newidea.component.tools.StringUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.*;
import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import sun.awt.OSInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 提供注册到Spring的多个BeanDefinition实例
 *
 * @author wzy
 * @date 2021/8/5 2:46 下午
 **/
@Slf4j
public class RedissonClientBeanDefinitionHolder {

    private Map<String, BeanDefinition> redissonClientMap = new ConcurrentHashMap<>();

    private void init(String dataSourceName, RedissonMetaInfo redissonMetaInfo) {
        long start = System.currentTimeMillis();

        Config config = buildConfig(dataSourceName, redissonMetaInfo);

        Config redissonConfig;

        switch (redissonMetaInfo.getType()) {
            case SINGLE:
                redissonConfig = buildSingleServer(config, redissonMetaInfo);
                break;
            case CLUSTER:
                redissonConfig = clusterServersConfig(config, redissonMetaInfo);
                break;
            case REPLICATED:
                redissonConfig = replicatedConfig(config, redissonMetaInfo);
                break;
            case SENTINAL:
                redissonConfig = sentinelConfig(config, redissonMetaInfo);
                break;
            case MASTER_SLAVE:
                redissonConfig = masterSlaveConfig(config, redissonMetaInfo);
                break;
            default:
                throw new IllegalArgumentException("未提供component.redisson.type配置，或者配置值不在枚举范围内");
        }

        //创建beanDefinition
        AbstractBeanDefinition redissonClientBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(RedissonClient.class,
                () -> Redisson.create(redissonConfig)).getBeanDefinition();

        redissonClientMap.put(dataSourceName, redissonClientBeanDefinition);

        if (log.isDebugEnabled()) {
            log.debug("redisson 启动耗时：{}", System.currentTimeMillis() - start);
        }
    }

    private Config buildConfig(String dataSourceName, RedissonMetaInfo redissonMetaInfo) {
        Config config = new Config();

        OSInfo.OSType osType = OSInfo.getOSType();

        config.setEventLoopGroup(new NioEventLoopGroup());
        config.setTransportMode(TransportMode.NIO);

        if (redissonMetaInfo.getTransportMode() != null) {
            if (TransportMode.EPOLL.equals(redissonMetaInfo.getTransportMode())) {
                if (OSInfo.OSType.LINUX.equals(osType)) {
                    config.setEventLoopGroup(new EpollEventLoopGroup());
                    config.setTransportMode(TransportMode.EPOLL);
                }
            } else if (TransportMode.KQUEUE.equals(redissonMetaInfo.getTransportMode())) {
                if (OSInfo.OSType.MACOSX.equals(osType)) {
                    config.setEventLoopGroup(new KQueueEventLoopGroup());
                    config.setTransportMode(TransportMode.KQUEUE);
                }
            }
        }
        // 非常重要，不可更改，会影响既有数据存储
        config.setCodec(new StringCodec());
        return config;
    }

    /**
     * 获取对应数据源的RedissonClient的BeanDefinition
     *
     * @param dataSourceName   数据源名称
     * @param redissonMetaInfo 当前数据源配置源信息
     * @return Bean定义
     */
    public BeanDefinition get(String dataSourceName, RedissonMetaInfo redissonMetaInfo) {
        if (null == redissonClientMap.get(dataSourceName)) {
            init(dataSourceName, redissonMetaInfo);
        }
        return redissonClientMap.get(dataSourceName);
    }

    /**
     * 设置基础参数
     *
     * @param serverConfig 基础连接参数
     */
    private void setBaseConfig(BaseConfig serverConfig, RedissonMetaInfo redissonMetaInfo) {
        if (null != redissonMetaInfo.getPassword()) {
            serverConfig.setPassword(redissonMetaInfo.getPassword());
        }

        serverConfig.setClientName(redissonMetaInfo.getClientName());

        serverConfig.setSubscriptionsPerConnection(redissonMetaInfo.getSubscriptionsPerConnection());

        serverConfig.setConnectTimeout(redissonMetaInfo.getConnectTimeout());
        serverConfig.setIdleConnectionTimeout(redissonMetaInfo.getIdleConnectionTimeout());
        serverConfig.setTimeout(redissonMetaInfo.getTimeout());

        serverConfig.setRetryAttempts(redissonMetaInfo.getRetryAttempts());
        serverConfig.setRetryInterval(redissonMetaInfo.getRetryInterval());
    }

    /**
     * 设置主从模式的通用性配置
     *
     * @param slaveConfig 主从模式基类
     */
    private void setMasterSlaveConfig(BaseMasterSlaveServersConfig slaveConfig, RedissonMetaInfo redissonMetaInfo) {
        slaveConfig.setMasterConnectionMinimumIdleSize(redissonMetaInfo.getMasterConnectionMinimumIdleSize());
        slaveConfig.setMasterConnectionPoolSize(redissonMetaInfo.getMasterConnectionPoolSize());

        slaveConfig.setSlaveConnectionMinimumIdleSize(redissonMetaInfo.getSlaveConnectionMinimumIdleSize());
        slaveConfig.setSlaveConnectionPoolSize(redissonMetaInfo.getSlaveConnectionPoolSize());

        slaveConfig.setSubscriptionConnectionMinimumIdleSize(redissonMetaInfo.getSubscriptionConnectionMinimumIdleSize());
        slaveConfig.setSubscriptionConnectionPoolSize(redissonMetaInfo.getSubscriptionConnectionPoolSize());

        slaveConfig.setSubscriptionMode(redissonMetaInfo.getSubscriptionMode());

        slaveConfig.setReadMode(redissonMetaInfo.getReadMode());

        try {
            if (null != redissonMetaInfo.getLoadBalancer()) {
                LoadBalancer loadBalancer = (LoadBalancer) ClassLoaderUtil.loadClass(redissonMetaInfo.getLoadBalancer()).newInstance();
                slaveConfig.setLoadBalancer(loadBalancer);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("配置的[{}]无法实例化，默认使用轮询", redissonMetaInfo.getLoadBalancer());
            slaveConfig.setLoadBalancer(new RoundRobinLoadBalancer());
        }
    }

    /**
     * 构建集群模式的配置
     *
     * @param config 基础配置
     * @return redisson连接客户端
     */
    private Config sentinelConfig(Config config, RedissonMetaInfo redissonMetaInfo) {
        Preconditions.checkNotNull(redissonMetaInfo.getAddress(), "哨兵模式必须提供连接地址：component.redisson.address");
        Preconditions.checkNotNull(redissonMetaInfo.getMasterName(), "哨兵模式必须提供主节点名称：component.redisson.masterName");

        SentinelServersConfig serverConfig = config.useSentinelServers();

        setBaseConfig(serverConfig, redissonMetaInfo);
        setMasterSlaveConfig(serverConfig, redissonMetaInfo);

        serverConfig.setMasterName(redissonMetaInfo.getMasterName());
        serverConfig.addSentinelAddress(StringUtils.split(redissonMetaInfo.getAddress(), ","));

        if (null != redissonMetaInfo.getSentinelPassword()) {
            serverConfig.setSentinelPassword(redissonMetaInfo.getSentinelPassword());
        }
        serverConfig.setDatabase(redissonMetaInfo.getDatabase());


        return config;
    }


    /**
     * 构建云托管模式的配置(如：阿里云)
     *
     * @param config 基础配置
     * @return redisson连接客户端
     */
    private Config replicatedConfig(Config config, RedissonMetaInfo redissonMetaInfo) {
        Preconditions.checkNotNull(redissonMetaInfo.getAddress(), "云托管模式必须提供连接地址：component.redisson.address");

        ReplicatedServersConfig serverConfig = config.useReplicatedServers();
        serverConfig.addNodeAddress(StringUtils.split(redissonMetaInfo.getAddress(), ","));
        setBaseConfig(serverConfig, redissonMetaInfo);
        setMasterSlaveConfig(serverConfig, redissonMetaInfo);


        serverConfig.setDatabase(redissonMetaInfo.getDatabase());
        serverConfig.setScanInterval(redissonMetaInfo.getClusterScanInterval());

        return config;
    }

    /**
     * 主从模式
     *
     * @param config 基础配置
     * @return redisson连接客户端
     */
    private Config masterSlaveConfig(Config config, RedissonMetaInfo redissonMetaInfo) {
        Preconditions.checkNotNull(redissonMetaInfo.getAddress(), "主从模式必须提供主节点连接地址：component.redisson.address");
        Preconditions.checkNotNull(redissonMetaInfo.getSlaveAddress(), "主从模式必须提供从节点连接地址:component.redisson.slaveAddress");

        MasterSlaveServersConfig serverConfig = config.useMasterSlaveServers();
        serverConfig.setMasterAddress(redissonMetaInfo.getAddress());
        serverConfig.setSlaveAddresses(Sets.newHashSet(StringUtils.split(redissonMetaInfo.getSlaveAddress(), ",")));

        setBaseConfig(serverConfig, redissonMetaInfo);
        setMasterSlaveConfig(serverConfig, redissonMetaInfo);

        serverConfig.setDatabase(redissonMetaInfo.getDatabase());

        return config;
    }

    /**
     * 构建单实例的redis
     *
     * @param config 基础配置
     * @return redisson连接客户端
     */
    private Config buildSingleServer(Config config, RedissonMetaInfo redissonMetaInfo) {
        Preconditions.checkNotNull(redissonMetaInfo.getAddress(), "单实例模式必须提供连接地址：component.redisson.address");

        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress(redissonMetaInfo.getAddress());

        setBaseConfig(serverConfig, redissonMetaInfo);

        serverConfig.setDatabase(redissonMetaInfo.getDatabase());

        serverConfig.setSubscriptionConnectionMinimumIdleSize(redissonMetaInfo.getSubscriptionConnectionMinimumIdleSize());
        serverConfig.setSubscriptionConnectionPoolSize(redissonMetaInfo.getSubscriptionConnectionPoolSize());

        serverConfig.setConnectionMinimumIdleSize(redissonMetaInfo.getConnectionMinimumIdleSize());
        return config;
    }

    /**
     * 构建集群模式的配置
     *
     * @param config 基础配置
     * @return redisson连接客户端
     */
    private Config clusterServersConfig(Config config, RedissonMetaInfo redissonMetaInfo) {
        Preconditions.checkNotNull(redissonMetaInfo.getAddress(), "集群模式必须提供连接地址：component.redisson.address");

        ClusterServersConfig serverConfig = config.useClusterServers();
        serverConfig.addNodeAddress(StringUtils.split(redissonMetaInfo.getAddress(), ","));

        setBaseConfig(serverConfig, redissonMetaInfo);
        setMasterSlaveConfig(serverConfig, redissonMetaInfo);

        return config;
    }
}
