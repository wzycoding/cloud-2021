package com.wzy;

import com.bxm.newidea.component.redisson.enums.RedissonClientTypeEnum;
import lombok.Data;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Value;

/**
 * Redisson单个数据源的配置信息
 *
 * @author wzy
 * @version 1.0
 * @date 2021/8/5 2:38 下午
 */
@Data
public class RedissonMetaInfo {
    /**
     * 客户端类型
     * SINGLE: 单实例模式
     * CLUSTER : 集群模式
     * REPLICATED ：云托管模式
     * MASTER_SLAVE ：主从模式
     * SENTINAL : 哨兵模式
     */
    private RedissonClientTypeEnum type = RedissonClientTypeEnum.SINGLE;

    /**
     * 连接地址,如果是集群模式，可以通过逗号进行分隔多个地址
     */
    private String address;

    /**
     * 鉴权密码，如果为空表示不需要进行鉴权
     */
    private String password;

    /**
     * 传输模式支持 NIO,基于Netty的EPOLL、KQUEUE
     * redisson默认为NIO
     * epoll的性能优于NIO，需要引入netty-transport-native-epoll包
     * <p>
     * kqueue适用于macOS，如需配置，需要引入netty-transport-native-kqueue(未适配，也不需要考虑)
     */
    private TransportMode transportMode = TransportMode.NIO;

    /**
     * ======================================================
     * 以下为通用性配置
     * ======================================================
     * <p>
     * 连接空闲超时，单位：毫秒
     * 如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉
     */
    private int idleConnectionTimeout = 10000;

    /**
     * 连接超时，单位：毫秒
     * 同任何节点建立连接时的等待超时
     */
    private int connectTimeout = 5000;

    /**
     * 命令等待超时，单位：毫秒
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时。
     */
    private int timeout = 200;

    /**
     * 命令失败重试次数
     * 如果尝试达到 retryAttempts（命令失败重试次数）
     * 仍然不能将命令发送至某个指定的节点时，将抛出错误。
     * 如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时
     */
    private int retryAttempts = 3;

    /**
     * 命令重试发送时间间隔，单位：毫秒
     * 在某个节点执行相同或不同命令时，连续 失败 failedAttempts（执行失败最大次数） 时，
     * 该节点将被从可用节点列表里清除，直到 reconnectionTimeout（重新连接时间间隔） 超时以后再次尝试
     */
    private int retryInterval = 100;

    /**
     * 单个连接最大订阅数量
     */
    private int subscriptionsPerConnection = 5;

    /**
     * 发布和订阅连接的最小空闲连接数
     * 用于发布和订阅连接的最小保持连接数（长连接）。
     * Redisson内部经常通过发布和订阅来实现许多功能。
     * 长期保持一定数量的发布订阅连接是必须的。
     */
    private int subscriptionConnectionMinimumIdleSize = 5;

    /**
     * 发布和订阅连接池大小
     * 用于发布和订阅连接的连接池最大容量。连接池的连接数量自动弹性伸缩
     */
    private int subscriptionConnectionPoolSize = 50;

    /**
     * 在Redis节点里显示的客户端名称
     */
    @Value("${spring.application.name:redisson_client}")
    private String clientName;

    /**
     * ======================================================
     * 以下为集群、哨兵、主从模式通用配置，阿里云集群配置也可以采用
     * ======================================================
     * 集群扫描间隔（毫秒）
     */
    private int clusterScanInterval = 2000;

    /**
     * 从节点最小空闲连接数
     */
    private int slaveConnectionMinimumIdleSize = 32;

    /**
     * 从节点连接池大小
     */
    private int slaveConnectionPoolSize = 64;

    /**
     * 主节点最小空闲连接数
     */
    private int masterConnectionMinimumIdleSize = 32;

    /**
     * 主节点最小空闲连接数
     */
    private int masterConnectionPoolSize = 62;

    /**
     * 负载均衡算法类的选择
     * org.redisson.connection.balancer.WeightedRoundRobinBalancer - 权重轮询调度算法
     * org.redisson.connection.balancer.RoundRobinLoadBalancer - 轮询调度算法
     * org.redisson.connection.balancer.RandomLoadBalancer - 随机调度算法
     */
    private String loadBalancer = "org.redisson.connection.balancer.RoundRobinLoadBalancer";

    /**
     * 订阅操作的负载均衡模式
     * 默认"SLAVE"，表示仅在从节点读取
     * 支持：MASTER、SLAVE、MASTER_SLAVE
     */
    private ReadMode readMode = ReadMode.SLAVE;

    /**
     * 订阅操作的负载均衡模式
     */
    private SubscriptionMode subscriptionMode = SubscriptionMode.SLAVE;

    /**
     * ======================================================
     * 以下为哨兵模式特有配置
     * ======================================================
     * 主服务器的名称
     */
    private String masterName;

    /**
     * 哨兵服务器密码
     */
    private String sentinelPassword;

    /**
     * ======================================================
     * 以下为主从模式特有配置
     * ======================================================
     * 添加从主节点地址
     */
    private String slaveAddress;

    /**
     * ======================================================
     * 以下为单实例、哨兵模式特有配置
     * ======================================================
     * 数据库编号
     */
    private int database = 0;

    /**
     * 最小空闲连接
     */
    private int connectionMinimumIdleSize = 24;
}