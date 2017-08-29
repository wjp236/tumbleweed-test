package com.tumbleweed.test.base.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.Test;
import com.tumbleweed.test.yuntongxun.util.StringUtil;

import java.math.BigDecimal;

/**
 * Created by mylover on 12/24/15.
 */
public class BigDecimalTest {

    public Logger log = LogManager.getLogger(BigDecimalTest.class);

    @Test
    public void test1() {
        ThreadContext.push(StringUtil.getUUID4MD5());
        BigDecimal b1 = new BigDecimal(500);
        BigDecimal b2 = new BigDecimal(501);

        log.info(b1);
        log.info(b2);

        if (b2.compareTo(b1) < 0) {
            log.info("true");
        }

        log.info("----- {}", b2.compareTo(b1));
    }

    @Test
    public void test2() {
        Integer i = 7;
        Integer j = 7;
        if (i == j) {
            log.info("-----true");
        }

        Integer m = -128;
        Integer n = -128;
        if (m == n) {
            log.info("-----true-----");
        }
    }

    @Test
    public void test3() {
        TestModel testModel = new TestModel();
        log.info(BigDecimal.valueOf(testModel.getI()));
    }


    @Test
    public void test4() {
        double t1 = 0.01;
        double t2 = 0.02;

        BigDecimal b1 = new BigDecimal(t1);
        BigDecimal b2 = new BigDecimal(t2);

        log.info(b1.multiply(b2));

    }

}

class TestModel {
    private double i;

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }
}
