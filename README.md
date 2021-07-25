# 1、框架版本定义

SpringCloud 版本：Hoxton.SR9
SpringBoot 版本：2.3.2.RELEASE
jdk：Java8
maven：3.5及以上
mysql：5.7及以上

SpringCloudAlibaba版本：2.2.6.RELEASE
Nacos版本：2.0.1，2.0.1的性能提升10倍，并且兼容1.x客户端，



# 2、项目说明

本项目演示两套微服务架构体系：

第一套为相对传统，其中包含部分组件Netflix公司已经停更，甚至停止维护，不过业界使用的公司非常多。

Eureka + openFeign + Zuul网关 + Hystrix + SpringCloud Config + SpringCloud Bus + Spring Cloud Stream



第二套为当下十分主流的，以SpringCloudAlibaba为主的微服务架构：

Nacos + Dubbo + Gateway网关 + Sentinal + RocketMQ + Seata（分布式事务）



# 3、参考资料

> SpringCloud文档

https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/

> SpringCloud中文文档

https://www.bookstack.cn/read/spring-cloud-docs/docs-project-SpringCloudStream.md

# 4、版本对应文档

这些文档一般在官网都可以找的到，这里集中整理到这里了。

> SpringBoot与SpringCloud版本对应，官网上已经给出了SpringCloud和SpringBoot的版本对应关系（中间），请严格遵守

https://spring.io/projects/spring-cloud#overview

> SpringCloudAlibaba的版本对应

https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

> Nacos2.x兼容1.x客户端说明

https://nacos.io/zh-cn/docs/2.0.0-compatibility.html