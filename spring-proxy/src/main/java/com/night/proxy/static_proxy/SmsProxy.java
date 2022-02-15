package com.night.proxy.static_proxy;

/**
 * 代理类
 *
 * @author night
 */
public class SmsProxy implements SmsService{
    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        // 调用方法前
        System.out.println("before method send()");
        smsService.send(message);
        // 调用方法后
        System.out.println("after method send()");
        return null;
    }
}
