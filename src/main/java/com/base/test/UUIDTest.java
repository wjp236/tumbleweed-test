package com.base.test;

import com.base.util.UUIDUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public  class UUIDTest {

    public Logger log = LogManager.getLogger(UUIDTest.class);

    @org.junit.Test
    public void test() throws NoSuchAlgorithmException, IOException {
        String requestId = UUIDUtils.randomUUID().toString();
        System.out.println(requestId);
    }

}

