package com.tumbleweed.test.base.proxy.jdk;

import com.tumbleweed.test.base.proxy.jdk.MyProxy.WangjpProxy;
import com.tumbleweed.test.base.proxy.jdk.MyProxy.WangjpProxyHandler;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * 描述: class
 *
 * @author: mylover
 * @Time: 13/02/2017.
 */
public class Test {

    @org.junit.Test
    public void testDynamic() throws Throwable {
        People people = (People) Proxy.newProxyInstance(
                People.class.getClassLoader(), new Class[]{People.class}, new ProxyHandler(new Wangjp()));

        people.eat();

        System.out.println(people.getClass().getName());//$Proxy2

        createProxyClassFile();
    }

    //还原代理类
    public static void createProxyClassFile() {

        //就是一个字节码数组,    .class文件流
        byte[] data = ProxyGenerator.generateProxyClass("$ProxyWjp", new Class[]{People.class});

        try {
            FileOutputStream out = new FileOutputStream("class/$ProxyWjp.class");
            out.write(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testWangDynamic() throws Throwable {
        People people = (People) WangjpProxy.createProxyInstance(
                People.class.getClassLoader(), People.class, new WangjpProxyHandler(new Wangjp()));

        people.eat();

        System.out.println(people.getClass().getName());
    }

}
