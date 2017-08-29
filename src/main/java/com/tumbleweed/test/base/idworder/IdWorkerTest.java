package com.tumbleweed.test.base.idworder;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 11/01/2017.
 */
public class IdWorkerTest {

    public Logger log = LogManager.getLogger(IdWorkerTest.class);

    private static long datacenterId = Long.valueOf(IPUtils.getLocalIp().substring(IPUtils.getLocalIp().length() - 1));

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    @Test
    public void test6() {
        IdWorker idWorker = new IdWorker(9, datacenterId);

        long date = (idWorker.nextId() >>> 20) & 0x1FFFFFFFFFFL;
        String dateStr = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSSS");

        log.info(dateStr);
    }

    @Test
    public void test2() {
        log.info(generateShortUuid());
    }


    @Test
    public void test1() {

        log.info("localIP:{}",IPUtils.getLocalIp());

        log.info("substring: {}", IPUtils.getLocalIp().length() - 1);

        log.info("192.168.2.1".substring(IPUtils.getLocalIp().length() - 1));

        log.info("datacenterId: {}", datacenterId);

        IdWorker idWorker = new IdWorker(9, datacenterId);

        long id = idWorker.nextId();

        log.info(id);

        log.info(String.valueOf(id).length());

        log.info(System.currentTimeMillis()/1000);
    }

}
