package com.tumbleweed.test.base.proxy.jdk.MyProxy;

import com.tumbleweed.test.base.proxy.jdk.People;

import java.lang.reflect.Method;

/**
 * 描述:代理执行类
 *
 *  通过反射调用方法
 *
 * @author: mylover
 * @Time: 15/02/2017.
 */
public class WangjpProxyHandler implements WangjpInvocationHandler {

    People people = null;

    public WangjpProxyHandler(People people) {
        this.people = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object args) throws Throwable {

        before();

        method.invoke(people, null);

        after();

        return null;
    }


    private void before() {

        System.out.println("吃饭前洗手");

    }

    private void after() {

        System.out.println("吃饭后刷牙");

    }

}
