package com.base.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class SystemTest {

    @org.junit.Test
    public void test() throws NoSuchAlgorithmException, IOException {
        String test = System.getProperty("user.dir");
//        System.out.print(test);
    }

    @org.junit.Test
    public void test1() {
        int a = 10;
        int b = 10;
        method(a, b);//打印a:100,b:200

        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }

    private void method(int a, int b) {
//        System.out.println("a=100,b=200");
//        System.exit(0);

    }

}
