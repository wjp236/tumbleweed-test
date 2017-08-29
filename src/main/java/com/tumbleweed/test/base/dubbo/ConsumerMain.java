package com.tumbleweed.test.base.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mylover on 09/11/2016.
 */
public class ConsumerMain {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo-client.xml"});
        context.start();

        TestDubbo demoService = (TestDubbo) context.getBean("testDubbo");

        demoService.sayHello();
    }
}
