package com.night.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "directQueue")
public class DirectReceiver {

    @RabbitHandler
    public void receiverMsg(String message){
        log.info(message);
    }
}
