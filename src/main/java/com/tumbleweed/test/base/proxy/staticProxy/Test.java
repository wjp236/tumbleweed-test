package com.tumbleweed.test.base.proxy.staticProxy;

/**
 * 描述:测试
 *
 * @author: mylover
 * @Time: 13/02/2017.
 */
public class Test {

    @org.junit.Test
    public void testStaticProxy() {
        People people = new WangjpDad(new Wangjp());
        people.zhaoduixiang();
    }

    @org.junit.Test
    public void testStaticProxyWangjp() {
        People people = new Wangjp();
        people.zhaoduixiang();
    }

}
