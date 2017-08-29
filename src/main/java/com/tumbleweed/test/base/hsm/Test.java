package com.tumbleweed.test.base.hsm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Test {

    public Logger log = LogManager.getLogger(Test.class);

    @org.junit.Test
    public void test() throws NoSuchAlgorithmException, IOException {

        String s = "000741303030303058";
        String result = String.valueOf(Integer.parseInt(s,16));
        System.out.println(result);

    }
}
