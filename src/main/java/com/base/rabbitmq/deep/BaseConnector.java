package com.base.rabbitmq.deep;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by mylover on 29/11/2016.
 */
public class BaseConnector {

    private final static String USER_PASS = "developer";
    private final static String HOST = "10.37.148.195";
    private final static int POST = 5672;

    protected Channel channel;
    protected Connection connection;
    protected String queueName;


    public BaseConnector(String queueName) throws IOException, TimeoutException {

        this.queueName = queueName;

        //打开连接和创建频道
        ConnectionFactory factory = new ConnectionFactory();

        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost(HOST);
        factory.setPort(POST);

        factory.setUsername(USER_PASS);
        factory.setPassword(USER_PASS);

        //创建连接
        connection = factory.newConnection();

        //创建频道
        channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(queueName, false, false, false, null);
    }

}
