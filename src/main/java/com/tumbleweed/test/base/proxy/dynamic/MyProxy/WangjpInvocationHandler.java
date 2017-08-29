package com.tumbleweed.test.base.proxy.dynamic.MyProxy;

import java.lang.reflect.Method;

/**
 * 描述:动态代理接口
 *
 * @author: mylover
 * @Time: 15/02/2017.
 */
public interface WangjpInvocationHandler {

    public Object invoke(Object proxy, Method method, Object args) throws Throwable;
}
