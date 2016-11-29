package com.base.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

/**
 * Created by mylover on 29/11/2016.
 */
public class MsgSend {

    public Logger logger = LogManager.getLogger(MsgSend.class);

    private final static String QUEUE_NAME = "test_queue_20161029";
    private final static String USER_PASS = "developer";
    private final static String HOST = "10.37.148.195";
    private final static int POST = 5672;

    @Test
    public void main() throws java.io.IOException, TimeoutException {
        /** * 创建连接连接到MabbitMQ */
        ConnectionFactory factory = new ConnectionFactory();

        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost(HOST);
        factory.setPort(POST);

        factory.setUsername(USER_PASS);
        factory.setPassword(USER_PASS);

        //创建一个连接
        Connection connection = factory.newConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送的消息
        String message = "hello world";
        //往队列中发出一条消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        logger.info("send msg: {}", message);

        //关闭频道和连接
        channel.close();
        connection.close();
    }




}
