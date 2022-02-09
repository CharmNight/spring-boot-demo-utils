# Web Socket 的代码Demo

实现了
- webSocket 连接
- 向后台发布信息
- 后端推送信息

view下的html可直接运行进行查看演示

## 注意 
用户名写死为 `CharmNight` 具体代码查看 `WebSocketConfig.class`

```
# 测试后端推送的curl
curl -H "Content-Type: application/json" -X POST -d '{"name":"CharmNight", "message":"测试推送内容"}'  http://localhost:9999/sendMessage
```
