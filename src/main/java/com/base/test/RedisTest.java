package com.base.test;

import com.base.common.JedisClusterUtils;
import com.base.common.JedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * Created by mylover on 10/30/15.
 */
public class RedisTest {

    public Logger log = LogManager.getLogger(RedisTest.class);

    @Test
    public void test1() {

        Jedis jedis = JedisUtil.createJedis("10.4.89.161", 6379);

        jedis.set("k001", "1");

        jedis.incrBy("k001", 5);

        jedis.set("k001", "10");

        jedis.incrBy("k001", 10);

        String value = jedis.get("k001");

        log.info("value:" + value);
    }

    @Test
    public void test11() {

        JedisCluster jedisCluster = JedisClusterUtils.getJedisCluster();

        jedisCluster.incrByFloat("k001", 1.11);

        String value = jedisCluster.get("k001");

        log.info("value:" + value);

    }

}
