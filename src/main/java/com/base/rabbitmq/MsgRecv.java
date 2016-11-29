package com.base.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by mylover on 29/11/2016.
 */
public class MsgRecv {

    private final static Logger logger = LogManager.getLogger(MsgRecv.class);

    private final static String QUEUE_NAME = "test_queue_20161029";
    private final static String USER_PASS = "developer";
    private final static String HOST = "10.37.148.195";
    private final static int POST = 5672;

    @Test
    public void main() throws IOException, TimeoutException, InterruptedException {
        //打开连接和创建频道，与发送端一样
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost(HOST);
        factory.setPort(POST);

        factory.setUsername(USER_PASS);
        factory.setPassword(USER_PASS);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //指定消费队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            logger.info("receiver msg: {}", message);
        }

    }

}
