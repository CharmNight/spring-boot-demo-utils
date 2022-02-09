package com.night.controller;

import com.night.bean.MessageDTO;
import com.night.handler.MyWebSocketHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {


    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody MessageDTO request){
        MyWebSocketHandler.sendMessage(request.getName(), request.getMessage());
        return "success";
    }

}
