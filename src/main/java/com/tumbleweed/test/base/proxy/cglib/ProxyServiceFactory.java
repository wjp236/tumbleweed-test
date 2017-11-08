package com.tumbleweed.test.base.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public class ProxyServiceFactory {

    public static Wangjp getProxyInstance(CglibProxyHandler myProxy) {

        Enhancer enhancer = new Enhancer();

        // 将Enhancer中的superclass属性赋值成BookServiceBean
        enhancer.setSuperclass(Wangjp.class);

        // 将Enhancer中的callbacks属性赋值成myProxy
        enhancer.setCallback(myProxy);

        return (Wangjp) enhancer.create();
    }
}
