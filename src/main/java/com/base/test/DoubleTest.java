package com.base.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by mylover on 6/22/16.
 */
public class DoubleTest {


    public Logger log = LogManager.getLogger(DoubleTest.class);

    @org.junit.Test
    public void testIsN() {
        Double i = 1.0;
        if (Double.isNaN(i)) {
            log.info("nis");
        } else {
            log.info("is");

        }
    }

    @org.junit.Test
    public void testRound() {
        double x = 9.9;
        log.info(Math.round(x));
    }

    @org.junit.Test
    public void test0() {
        Double i = 0.0;
        if (i == 0) {
            log.info("true");
        }
    }

}
