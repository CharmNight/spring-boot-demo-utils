package com.night.proxy.static_proxy;

/**
 * 业务实现
 * @author night
 */
public class SmsServiceImpl implements SmsService{
    @Override
    public String send(String message) {
        System.out.println("Send Message" + message);
        return message;
    }
}
