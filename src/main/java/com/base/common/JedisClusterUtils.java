package com.base.common;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 获取redis连接
 * 
 * @author QIANG
 *
 */
public class JedisClusterUtils {

	private static Logger LOG = LogManager.getLogger(JedisClusterUtils.class);

	private static JedisCluster jedisCluster = null;

	static {
        String redisMaxTotal = "1000";
		String redisMaxIdle = "10";
		String redisClusterIp = "10.4.89.161:6379";
		String redisTimeOut = "5000";
		String redismaxRedirections = "10";


        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(Integer.valueOf(redisMaxIdle));

		Set<HostAndPort> hps = new HashSet<>();
		String[] ip = redisClusterIp.split(":");
		int port = Integer.valueOf(ip[1]);
		hps.add(new HostAndPort(ip[0], port));

		jedisCluster = new JedisCluster(hps, 5000, 1000, config);

		Map<String, JedisPool> nodes = jedisCluster.getClusterNodes();
		LOG.info("Get the redis thread pool:{}", nodes.toString());
	}

	public static JedisCluster getJedisCluster() {
		return jedisCluster;
	}

}
