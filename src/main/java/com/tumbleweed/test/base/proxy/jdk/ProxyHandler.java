package com.tumbleweed.test.base.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述:声明
 *
 * @author: mylover
 * @Time: 13/02/2017.
 */
public class ProxyHandler implements InvocationHandler {

    People people = null;

    public ProxyHandler(People people) {
        this.people = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();

        method.invoke(people, args);

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
