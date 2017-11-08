package com.tumbleweed.test.base.proxy.spring;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 描述:返回结果时后的处理意见
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public class TicketServiceAfterReturningAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("AFTER_RETURNING：本次服务已结束....");
    }

}
