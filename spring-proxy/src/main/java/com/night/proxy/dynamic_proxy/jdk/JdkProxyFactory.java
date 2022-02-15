package com.night.proxy.dynamic_proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * 获取代理对象的工厂类
 *
 * @author night
 */
public class JdkProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                // 目标类的类加载
                target.getClass().getClassLoader(),
                // 代理需要实现的接口， 可以指定多个
                target.getClass().getInterfaces(),
                // 代理对象对应的自定义
                new DebugInvocationHandler(target)
        );
    }
}
