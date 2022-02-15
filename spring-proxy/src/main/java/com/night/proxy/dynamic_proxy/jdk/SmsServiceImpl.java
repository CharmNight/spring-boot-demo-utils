package com.night.proxy.dynamic_proxy.jdk;

/**
 * 接口实现
 *
 * @author night
 */
public class SmsServiceImpl implements SmsService{
    @Override
    public String send(String message) {
        System.out.println("send message " + message);
        return message;
    }
}
