# Redisson 
基于Redis 实现
- 数据缓存
- 锁、以及分布式锁

## 前置准备工作
### Redis 安装
```shell
# 拉取Redis 镜像
docker pull redis:latest
# 启动Redis
docker run -itd --name redis -p 6379:6379 redis
```

### POM导入
```shell
        <!-- 开始引入redisson-spring-boot-starter -->
        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson-spring-boot-starter</artifactId>
            <version>3.12.5</version>
        </dependency>
        <!-- 开始spring 缓存 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <!-- JSON解析 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.75</version>
        </dependency>

```


## 配置
`resources/redisson`下有个`redisson-demo.yml` 这个是用于配置的
需要在`application.yml`中指定

```yaml
spring:
  redis:
    redisson:
      config: classpath:redisson/redisson-demo.yml
```
可以查看`org.redisson.spring.starter.RedissonProperties`

## 代码
```shell
└─cache (数据缓存部分)
    ├─controller 缓存请求
    ├─service 缓存逻辑
    ├─utils   缓存工具类
└─config    (Redisson 配置部分)
└─serialize (序列化工具)
└─lock  (锁部分)    

```
    

## 锁
### 实现方案
#### SETNX
缺点：
1. 无法自动续约
2. 需要手动保证原子性 即 使用lua脚本
3. 无法适用集群环境

#### redisson.rlock
缺点：
1. 无法适用集群环境
2. 相对`SETNX` 比较沉重，相对性能稍有影响
