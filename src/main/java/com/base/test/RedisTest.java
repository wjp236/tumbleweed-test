package com.base.test;

import com.base.common.JedisClusterUtils;
import com.base.common.JedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mylover on 10/30/15.
 */
public class RedisTest {

    public Logger log = LogManager.getLogger(RedisTest.class);

    @Test
    public void test1() {

        Jedis jedis = JedisUtil.createJedis("123.56.228.198", 7000);

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

    @Test
    public void test2() {
        Set nodes = new HashSet();
        nodes.add(new HostAndPort("123.56.228.198", 7000));
//        nodes.add(new HostAndPort("123.56.228.198", 7001));
//        nodes.add(new HostAndPort("123.56.228.198", 7002));
//        nodes.add(new HostAndPort("123.56.228.198", 7003));
//        nodes.add(new HostAndPort("123.56.228.198", 7004));
//        nodes.add(new HostAndPort("123.56.228.198", 7005));
//        nodes.add(new HostAndPort("123.56.228.198", 7006));

        JedisCluster   js = new JedisCluster(nodes);

        js.set("k001", "testCluster");

        String test = js.get("k001");

        log.info(test);

    }

}
