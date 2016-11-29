package com.base.rabbitmq.deep;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by mylover on 29/11/2016.
 */
public class TestRMQ {

    private final static String QUEUE_NAME = "test_queue_20161029";

    private final static Logger logger = LogManager.getLogger(TestRMQ.class);

    @org.junit.Test
    public void testMq() throws IOException, TimeoutException, InterruptedException {

        Receiver receiver = new Receiver(QUEUE_NAME);
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();

        Sender sender = new Sender(QUEUE_NAME);
        for (int i = 0; ; i++) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setChannel("test");
            messageInfo.setContent("msg" + i);
            sender.sendMessage(messageInfo);
            logger.info("Message ( " + "channel : " + messageInfo.getChannel() + " , content : " + messageInfo.getContent() + " ) send.");
            Thread.sleep(5000);
        }
    }

}
