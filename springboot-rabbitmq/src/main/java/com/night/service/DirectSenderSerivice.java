package com.night.service;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class DirectSenderSerivice {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sender(int i){
        String date = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
        String content = i + "hello! " + date;

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend("amq.direct", "direct_key", content, correlationData);

    }
}
