/**
 * CopyRight 2015 必拓电子商务有限公司
 */
package com.tumbleweed.test.base.rabbitmq.spring;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * @Description: RabbitMq消费者配置
 * @see: QueueConsumer 此处填写需要参考的类
 * @version 2016年7月27日 上午10:45:52
 * @author wangjp<wangjp@enn.com>
 * @author wangjp<wangjp@enn.com>
 */
@Configuration
@PropertySource("classpath:/rabbitmq.properties")
public class FanoutRabbitMqConsumerConfig {

	/** 多地址列表,如果有多个主机地址可以使用这个方法,值格式ip1:port1,ip2:port2,... */
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
	 * @author wangjpb<wangjpb@enn.com>
	 */
	private CachingConnectionFactory initConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(addresses);
        connectionFactory.setPassword(pass);
        connectionFactory.setUsername(user);
		return connectionFactory;
	}

	/**
	 * @Description 连接工厂
	 * @return
	 * @throws IOException
	 */
	@Bean
	public ConnectionFactory queueConnectionFactory() throws IOException {
		CachingConnectionFactory connectionFactory = initConnectionFactory();
		Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);
        channel.queueDeclare(queue_name, true, false, false, null);
        channel.exchangeDeclare(exchange_name, "fanout");
        channel.queueBind(queue_name, exchange_name, routing_key, null);
		return connectionFactory;
	}

	/**
	 * 调度任务监听消息处理 （作者：wangjp<wangjp@enn.com>）
	 * 
	 * @return
	 */
	@Bean
	public MessageLiistenerHandler exchangesMessageLiistenerHandler() {
		return new MessageLiistenerHandler();
	}

	/**
	 * @throws IOException
	 * 
	 * @Title: listenerContainer
	 * @Description: 监听器
	 * @author wangjp<wangjp@enn.com>
	 * @return SimpleMessageListenerContainer 返回类型
	 * @throws
	 */
	@Bean
	public SimpleMessageListenerContainer listenerContainer()
			throws IOException {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(queueConnectionFactory());
        container.setQueueNames(queue_name);
		container.setMessageListener(new MessageListenerAdapter(exchangesMessageLiistenerHandler()));
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		return container;
	}

}
