package com.tumbleweed.test.base.proxy.spring;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 描述:抛出异常时的处理意见
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public class TicketServiceThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Exception ex){
        System.out.println("AFTER_THROWING....");
    }
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
        System.out.println("调用过程出错啦！！！！！");
    }

}
