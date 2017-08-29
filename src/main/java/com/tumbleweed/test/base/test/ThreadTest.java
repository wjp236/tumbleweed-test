package com.tumbleweed.test.base.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ThreadTest {

    public Logger logger = LogManager.getLogger(ThreadTest.class);

    @org.junit.Test
    public void test() throws NoSuchAlgorithmException, IOException, InterruptedException {

        logger.info("start");
        for (int i = 1; i <= 4; i++) {
            logger.info(i);
            if (i == 3) {
                break;
            }
        }
        logger.info("end");
    }

}
