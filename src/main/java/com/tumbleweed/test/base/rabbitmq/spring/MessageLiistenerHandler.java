package com.tumbleweed.test.base.rabbitmq.spring;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * 调度监听
 * 
 * @author wangjp<wangjp@enn.com>
 */
public class MessageLiistenerHandler implements ChannelAwareMessageListener {

	private static Logger logger = LoggerFactory.getLogger(MessageLiistenerHandler.class);

    /**
     * 消息监听
     * @param message
     * @param channel
     * @throws Exception
     */
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		String msg = new String(message.getBody(), "UTF-8");
		logger.info(String.format("message:\n %s", msg));
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        logger.info("\ntask over");
	}
}
