
## Fanout
订阅模型借助一个新的概念：Exchange（交换机）实现，不同的订阅模型本质上是根据交换机(Exchange)的类型划分的。

Fanout（广播模型）：
- 将消息发送给绑定交换机的所有队列（因为他们使用的是同一个 RoutingKey