package com.night.proxy.static_proxy;


/**
 * 测试类
 *
 * @author night
 */
public class Test {
    public static void main(String[] args) {
        SmsServiceImpl smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("Java");
    }
}
