package com.base.java8;

/**
 * Created by mylover on 08/11/2016.
 */
public interface InterfaceTest {

    double calculate(int a);

    default double sqrt(int a) {
        return 0;
    }

}
