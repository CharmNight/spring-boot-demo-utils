package com.night.proxy.dynamic_proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法拦截器
 *
 * @author night
 */
public class DebugMethodInterceptor implements MethodInterceptor {

    /**
     * @param o             代理对象（增强的对象）
     * @param method        被拦截的方法（需要增强的方法）
     * @param args          方法入参
     * @param methodProxy   用于调用原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 调用方法之前
        System.out.println("before method " + method.getName());
        Object result = methodProxy.invokeSuper(o, args);
        // 调用方法之后
        System.out.println("after method " + method.getName());
        return result;
    }
}
