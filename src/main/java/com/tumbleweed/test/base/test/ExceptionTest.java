package com.tumbleweed.test.base.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Created by mylover on 10/30/15.
 */
public class ExceptionTest {

    public Logger log = LogManager.getLogger(ExceptionTest.class);

    @Test
    public void test1() {
        this.testExcept();
    }

    private void testExcept() {
        if (true) {
            throw new RuntimeException();
        }
    }


}
