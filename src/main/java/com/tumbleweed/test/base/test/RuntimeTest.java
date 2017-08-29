package com.tumbleweed.test.base.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mylover on 03/11/2016.
 */
public class RuntimeTest {


    protected static final Logger logger = LoggerFactory.getLogger(RuntimeTest.class);


    @org.junit.Test
    public void test() {
        Runtime runtime = Runtime.getRuntime();
        int nrOfProcessors = runtime.availableProcessors();
        logger.info("Number of processors available to the Java Virtual Machine:{}", nrOfProcessors);
    }
}
