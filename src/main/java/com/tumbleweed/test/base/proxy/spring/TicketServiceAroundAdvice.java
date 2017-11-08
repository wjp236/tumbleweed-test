package com.tumbleweed.test.base.proxy.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public class TicketServiceAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("AROUND_ADVICE:BEGIN....");
        Object returnValue = methodInvocation.proceed();
        System.out.println("AROUND_ADVICE:END.....");
        return returnValue;
    }
}
