
## 环境准备
### 单机
```shell
docker pull mysql:5.7.35

docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 --name mysql -d 1d7aba917169
```

### PXC集群环境 + MyCat
## 安装PXC集群

### 镜像

```Bash
# 拉取镜像
docker pull percona/percona-xtradb-cluster

# 修改名称
docker tag percona/percona-xtradb-cluster pxc

# 移除原镜像
docker rmi docker.io/percona/percona-xtradb-cluster

```

### 创建net网络

```Bash
 # 创建net1 网段
 docker network create --subnet=172.20.0.0/16 net1
 
 # 创建数据卷
[root@VM-0-13-centos ~]# docker volume create --name mysql_1
[root@VM-0-13-centos ~]# docker volume create --name mysql_2
[root@VM-0-13-centos ~]# docker volume create --name mysql_3


```

### 创建PXC集群

```Bash

docker run -di --name=pn1 --net=net1 -p 3306:3306 -v mysql_1:/var/lib/mysql --privileged -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=cluster1 -e XTRABACKUP_PASSWORD=123456  pxc:5.7 

docker run -di --name=pn2 --net=net1 -p 3307:3306 -v mysql_2:/var/lib/mysql --privileged -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=cluster1 -e XTRABACKUP_PASSWORD=123456 -e CLUSTER_JOIN=pn1 pxc:5.7
  
docker run -di --name=pn3 --net=net1 -p 3308:3306 -v mysql_3:/var/lib/mysql --privileged -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=cluster1 -e XTRABACKUP_PASSWORD=123456 -e CLUSTER_JOIN=pn2 pxc:5.7  

```
### MyCat 环境准备
> MyCat 其实就是一个代理， 它并没有真正的数据存储， 仅仅作为转发的中间件存在
> 


```Bash
docker pull manondidi/mycat

mkidr -p /docker/mycat/conf

docker run -p 8066:8066 -d --name mycat manondidi/mycat

docker cp mycat:/usr/local/mycat/conf/rule.xml /docker/mycat/conf
docker cp mycat:/usr/local/mycat/conf/server.xml /docker/mycat/conf
docker cp mycat:/usr/local/mycat/conf/schema.xml /docker/mycat/conf

docker rm -f mycat #强制删除容器
docker run -p 8066:8066 -d --name mycat --net=net1 -v /docker/mycat/conf/rule.xml:/usr/local/mycat/conf/rule.xml -v /docker/mycat/conf/server.xml:/usr/local/mycat/conf/server.xml -v /docker/mycat/conf/schema.xml:/usr/local/mycat/conf/schema.xml manondidi/mycat

```

### 修改配置文件


## Druid （德鲁伊) 
> Druid首先是一个数据库连接池，但它不仅仅是一个数据库连接池，它还包含一个ProxyDriver，一系列内置的JDBC组件库，一个SQL Parser。
> 
> http://www.apache-druid.cn/GettingStarted/chapter-1.html#druid%E6%98%AF%E4%BB%80%E4%B9%88

## myBatis-Plus

### 基础使用
> 配置文件
> application指定Mapper

定义
- Bean
- UserService
- UserServiceImpl
- UserMapper


### 自定义

### 缓存设置

### 复杂SQL

### CAS锁（乐观锁)


## 多数据源