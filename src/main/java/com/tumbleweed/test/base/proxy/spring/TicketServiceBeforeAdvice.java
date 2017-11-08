package com.tumbleweed.test.base.proxy.spring;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 描述:执行RealSubject对象的方法之前的处理意见
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public class TicketServiceBeforeAdvice implements MethodBeforeAdvice {


    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("BEFORE_ADVICE: 欢迎光临代售点....");
    }

}
