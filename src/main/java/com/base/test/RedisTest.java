package com.base.test;

import com.base.common.JedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by mylover on 10/30/15.
 */
public class RedisTest {

    public Logger log = LogManager.getLogger(RedisTest.class);

    @Test
    public void test11() {

        Jedis jedis = JedisUtil.createJedis("10.4.89.161", 6379);
        jedis.set("k001", "------1------");

        log.info("redis:" + jedis.get("k001"));
    }

}
