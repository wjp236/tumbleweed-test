package com.tumbleweed.test.base.java8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Created by mylover on 08/11/2016.
 */
public class Java8Test {

    public Logger log = LogManager.getLogger(Java8Test.class);

    @Test
    public void test() {

        InterfaceTest interfaceTest = new InterfaceTestImpl();

        log.info(interfaceTest.sqrt(0));


    }

}
