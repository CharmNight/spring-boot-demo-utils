package com.night.proxy.dynamic_proxy.cglib;

public class Test {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) CglibProxyFactory.getProxy(SmsService.class);
        smsService.send(" java");
    }
}
