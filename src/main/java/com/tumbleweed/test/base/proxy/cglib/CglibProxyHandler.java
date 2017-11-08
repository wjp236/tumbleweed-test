package com.tumbleweed.test.base.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 描述: cglib proxy 声明
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public class CglibProxyHandler implements MethodInterceptor {

    //实现MethodInterceptor接口方法
    @Override
    public Object intercept(
            Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        System.out.println("调用的方法是：" + method.getName());
        System.out.println("实际调用者是： " + obj.getClass());
        for (Object objTemp : args) {
            System.out.println("方法参数类型为：" + objTemp.getClass());
        }

        before();

        //通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj, args);

        after();
        return result;
    }



    private void before() {

        System.out.println("吃饭前洗手");

    }

    private void after() {

        System.out.println("吃饭后刷牙");

    }
}
