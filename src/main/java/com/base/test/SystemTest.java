package com.base.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class SystemTest {

    public Logger logger = LogManager.getLogger(SystemTest.class);

    @org.junit.Test
    public void test() throws NoSuchAlgorithmException, IOException {
        String test = System.getProperty("user.dir");
//        System.out.print(test);
        logger.info(test);
    }

}
