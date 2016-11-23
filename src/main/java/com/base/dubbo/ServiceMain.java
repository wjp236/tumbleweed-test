package com.base.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mylover on 09/11/2016.
 */
public class ServiceMain {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{
                        "dubbo-service.xml"
                });
        context.start();

        TestDubbo testDubbo = (TestDubbo) context.getBean("testDubbo");

        testDubbo.sayHello();

    }
}

