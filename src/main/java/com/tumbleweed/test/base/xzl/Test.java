package com.tumbleweed.test.base.xzl;

/**
 * 描述:Test
 *
 * @author: mylover
 * @Time: 10/12/2017.
 */
public class Test {

    @org.junit.Test
    public void test1() {

        String r1 = "张三",r2 = "李四", r3 = "王五";

        String[] s = new String[3];

        s[0] = r1;
        s[1] = r2;
        s[2] = r3;

        for (String temp : s) {
            System.out.println("人名：" + temp);
        }

    }




}
