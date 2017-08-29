package com.tumbleweed.test.base.test;

import com.tumbleweed.test.enn.model.XinyipayPo;
import com.google.gson.Gson;
import com.jd.dd.glowworm.PB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Created by mylover on 10/30/15.
 */
public class SerializersTest {

    public Logger log = LogManager.getLogger(SerializersTest.class);

    @Test
    public void testPB() {

        XinyipayPo xinyipayPo = new XinyipayPo();
        xinyipayPo.setAccountDate("1111111111");
        xinyipayPo.setAppId("1234567890");
        xinyipayPo.setCash(10.0);
        xinyipayPo.setCurrency("123");
        xinyipayPo.setPayChannel("1");
        xinyipayPo.setPrivilegePrice(123.0);
        xinyipayPo.setRequestId("123456");
        xinyipayPo.setTradeAmount(10.0);
        xinyipayPo.setType("1");
        xinyipayPo.setXinyiShell(10.0);

        log.info("bytes length :"+PB.toPBBytes(xinyipayPo).length);

        long length = 1000000;

        double t1 = System.currentTimeMillis();

        for (int i = 0; i < length; i++) {

            byte[] bytes = PB.toPBBytes(xinyipayPo);

            XinyipayPo result = (XinyipayPo) PB.parsePBBytes(bytes);

        }

        double t2 = System.currentTimeMillis();

        double time = t2 - t1;

        log.info("time:" + time);

        double result = (t2 - t1)/length;

        log.info("arg:" + result);

    }

    @Test
    public void testGson() {

        Gson gson = new Gson();

        XinyipayPo xinyipayPo = new XinyipayPo();
        xinyipayPo.setAccountDate("1111111111");
        xinyipayPo.setAppId("1234567890");
        xinyipayPo.setCash(10.0);
        xinyipayPo.setCurrency("123");
        xinyipayPo.setPayChannel("1");
        xinyipayPo.setPrivilegePrice(123.0);
        xinyipayPo.setRequestId("123456");
        xinyipayPo.setTradeAmount(10.0);
        xinyipayPo.setType("1");
        xinyipayPo.setXinyiShell(10.0);

        log.info("bytes length :"+ gson.toJson(xinyipayPo).getBytes().length);

        long length = 1000000;

        double t1 = System.currentTimeMillis();


        for (int i = 0; i < length; i++) {

            String xinyipayStr = gson.toJson(xinyipayPo);

            XinyipayPo result = gson.fromJson(xinyipayStr, XinyipayPo.class);

        }

        double t2 = System.currentTimeMillis();

        double time = t2 - t1;

        log.info("time:" + time);

        double result = (t2 - t1)/length;

        log.info("arg:" + result);

    }


}
