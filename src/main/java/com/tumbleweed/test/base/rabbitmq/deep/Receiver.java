package com.tumbleweed.test.base.rabbitmq.deep;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by mylover on 29/11/2016.
 */
public class Receiver extends BaseConnector implements Consumer {

    private final static Logger logger = LogManager.getLogger(Receiver.class);

    public Receiver(String queueName) throws IOException, TimeoutException {
        super(queueName);
        try {
            channel.basicConsume(queueName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        channel.basicConsume(queueName, true, this);
    }

    //实现Runnable的run方法
    public void run() {
        try {
            channel.basicConsume(queueName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        MessageInfo messageInfo = (MessageInfo) SerializationUtils.deserialize(body);
        logger.info("Message ( " + "channel : " + messageInfo.getChannel() + " , content : " + messageInfo.getContent() + " ) received.");
    }

    /** * 下面这些方法都是实现Consumer接口的 **/
    //当消费者注册完成自动调用
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }

    @Override
    public void handleCancelOk(String consumerTag) {

    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

    }

    @Override
    public void handleRecoverOk(String consumerTag) {

    }

}
