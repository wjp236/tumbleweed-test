package com.tumbleweed.test.base.proxy.aspectJ;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 20/01/2018.
 */
public class Hello
{
    // 定义一个简单方法，模拟应用中的业务逻辑方法
    public void sayHello()
    {
        System.out.println("Hello AspectJ!");
    }
    // 主方法，程序的入口
    public static void main(String[] args)
    {
        Hello h = new Hello();
        h.sayHello();
    }
}
