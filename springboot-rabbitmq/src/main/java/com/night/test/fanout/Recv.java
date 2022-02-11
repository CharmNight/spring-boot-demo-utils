package com.night.test.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Recv {
    private final static String EXCHANGE_NAME = "hello";

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
            // 消费者将 channel 绑定在交换机上
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println("[*] Waiting for messages. To exit press CTRL+C");

            // 从 channel 中接收消息后回调
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("[x] Received '" + message + "'");
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
