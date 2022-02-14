package com.night.controller;

import com.night.service.DirectSenderSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    @Autowired
    private DirectSenderSerivice directSenderSerivice;

    @GetMapping("/send")
    public String sendMessage(){
        directSenderSerivice.sender(5);
        return "Success";
    }

}
