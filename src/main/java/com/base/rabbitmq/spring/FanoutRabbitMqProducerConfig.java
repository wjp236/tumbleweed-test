/**
 * CopyRight 2015 必拓电子商务有限公司
 */
package com.base.rabbitmq.spring;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * @Description: RabbitMq生产者配置
 * @see: RabbitmqProducer 此处填写需要参考的类
 * @version 2015年8月19日 上午10:42:24
 * @author wangjp<wangjp@enn.com>
 * @author John<wangcyg@enn.com>
 */
@Configuration
@PropertySource("classpath:/rabbitmq.properties")
public class FanoutRabbitMqProducerConfig {
	/** 地址 */
	@Value("${rabbitmq.addresses}")
	private String addresses;
	/** 登录用户名 */
	@Value("${rabbitmq.user}")
	private String user;
	/** 登录密码 */
	@Value("${rabbitmq.pass}")
	private String pass;

	public static String exchange_name = "fanout_logs";

    public static String queue_name = "pay.xinyipay";

    public static String routing_key = "";

    /**
	 * 链接工厂初始化
	 * 
	 * @author Liulei<liuleiah@enn.com>
	 */
	private CachingConnectionFactory initConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(addresses);
		connectionFactory.setUsername(user);
		connectionFactory.setPassword(pass);
		return connectionFactory;
	}

	/**
	 * @Description 连接工厂
	 * @return
	 * @throws IOException
	 */
	@Bean
	public ConnectionFactory connectionCreateProducerFactory()
			throws IOException {
		CachingConnectionFactory connectionFactory = initConnectionFactory();
		Connection connection = connectionFactory.createConnection();
		Channel channel = connection.createChannel(false);
        channel.exchangeDeclare(exchange_name, "fanout");
		return connectionFactory;
	}

	/**
	 * @Description 创建rabbitTemplate 消息模板类
	 * @return
	 * @throws IOException
	 */
	@Bean
	public RabbitTemplate rabbitCreateProducerTemplate()
			throws IOException {
		RabbitTemplate template = new RabbitTemplate(connectionCreateProducerFactory());
        return template;
	}

}
