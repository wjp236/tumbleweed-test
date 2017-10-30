package com.tumbleweed.test.base.lombok;

import org.junit.Test;

/**
 * 描述: 学习 lombok
 *
 * @author: mylover
 * @Time: 30/10/2017.
 */
public class XinyipayPoTest {

    @Test
    public void test1(){
        XinyipayPo xinyipayPo = new XinyipayPo();

        xinyipayPo.setAccountDate("12345678");


        System.out.println(xinyipayPo.toString());


        XinyipayPo xinyipayPo1 = XinyipayPo.builder().accountDate("12345678").type("1234").appId("12345678901112").build();

        System.out.println(xinyipayPo1.toString());
    }

}
