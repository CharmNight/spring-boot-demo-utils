package com.night.test.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Send {
    private final static String EXCHANGE_NAME = "hello_direct";

    public static void main(String[] args) {
        // 获取连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("110.42.170.186");
        factory.setPort(5672);
        factory.setUsername("root");
        factory.setPassword("123456");
        factory.setVirtualHost("/test");


        try {
            // 获取连接 可以在web view 的 connection部分看到新创建的连接
            Connection connection = factory.newConnection();
            // 创建通道 可以在web view 的 channels 看到创建的channel
            Channel channel = connection.createChannel();

            // 发送者声名一个交换机， 往这个交换机中发送消息， 参数2：交换机类型
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            for (int i = 0; i < 10; i++) {
                String message = args.length < 1 ? "info: Hello World!" : String.join(" ", args);
                channel.basicPublish(EXCHANGE_NAME, "/direct_log", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("[x] Send '" + message + "'");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
