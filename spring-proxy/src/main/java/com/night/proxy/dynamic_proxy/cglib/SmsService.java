package com.night.proxy.dynamic_proxy.cglib;

/**
 * 业务实现
 *
 * @author night
 */
public class SmsService {
    public String send(String message) {
        System.out.println("send message" + message);
        return message;
    }
}
