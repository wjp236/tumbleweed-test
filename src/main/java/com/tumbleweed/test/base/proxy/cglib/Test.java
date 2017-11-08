package com.tumbleweed.test.base.proxy.cglib;

/**
 * 描述:测试cglib动态代理
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public class Test {


    @org.junit.Test
    public void test1() throws Throwable {

        Wangjp wangjp = ProxyServiceFactory.getProxyInstance(new CglibProxyHandler());

        wangjp.eat();

    }


}
