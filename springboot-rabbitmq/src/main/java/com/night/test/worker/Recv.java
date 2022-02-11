package com.night.test.worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {

    private final static String QUEUE_NAME = "hello";

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
            // 设置每个消费者同时只能处理1条消息
            channel.basicQos(1);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("[x] Received '" + message + "'");

                // 手动ACK
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            });

            // 参数2 控制取消自动ACK
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {});
            System.out.println("[*] Waiting for messages. To exit press CTRL+C");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
