# RabbitMQ 接入 SpringBoot 的Demo

## 开发前准备
### RabbitMQ 安装 Docker安装
```shell
# 安装带有管理面板的 RabbitMQ
docker pull rabbitmq:management
# 执行
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management

# 创建新用户， 赋予权限
# 1. 进入容器内部
docker exec -i -t 7e9 /bin/bash
# 2. 执行rabbitmqctl add_user root 123456 添加用户，用户名为root,密码为1234
rabbitmqctl add_user root 123456 
# 3. 执行abbitmqctl set_permissions -p / root ".*" ".*" ".*" 赋予root用户所有权限
rabbitmqctl set_permissions -p / root ".*" ".*" ".*"
# 4. 执行rabbitmqctl set_user_tags root administrator赋予root用户administrator角色
rabbitmqctl set_user_tags root administrator
```

