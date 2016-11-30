package com.base.rabbitmq;

import com.rabbitmq.client.*;
import com.sun.deploy.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * Created by mylover on 29/11/2016.
 */
public class MsgSend {

    private final static String QUEUE_NAME = "test_queue_20161029";
    private final static String USER_PASS = "developer";
    private final static String HOST = "10.37.148.195";
    private final static int POST = 5672;

    private static Scanner scanner = new Scanner(System.in);
    public static String XCHG_NAME = "xchg_20161029";

    private static String message = "";


    //exchange type
    public enum XT {
        DEFAULT, DIRECT, TOPIC, HEADERS, FANOUT, RPC
    }

    public static void main(String[] args) throws java.io.IOException, TimeoutException, InterruptedException {
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

        XT xt = XT.HEADERS;

        String queueName = QUEUE_NAME;

        //指定一个队列
        switch (xt) {
            case DEFAULT: //默认，向指定的队列发送消息，消息只会被一个consumer处理,多个消费者消息会轮训处理,消息发送时如果没有consumer，消息不会丢失
                //为消息通道绑定一个队列
                //队列的相关参数需要与第一次定义该队列时相同，否则会出错
                //参数1：队列名称
                //参数2：为true时server重启队列不会消失
                //参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
                //参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
                //参数5：建立队列时的其他参数
                channel.queueDeclare(QUEUE_NAME, true, false, true, null);

                while (GetInputString()) {
                    //向server发布一条消息
                    //参数1：exchange名字，若为空则使用默认的exchange
                    //参数2：routing key
                    //参数3：其他的属性
                    //参数4：消息体
                    //RabbitMQ默认有一个exchange，叫default exchange，它用一个空字符串表示，它是direct exchange类型，
                    //任何发往这个exchange的消息都会被路由到routing key的名字对应的队列上，如果没有对应的队列，则消息会被丢弃
                    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes()); //设置消息为持久化，服务器重启不会丢失

                    System.out.println("Send " + message);
                }
                break;
            case FANOUT:
                //广播给所有队列  接收方也必须通过fanout交换机获取消息,所有连接到该交换机的consumer均可获取消息
                //如果producer在发布消息时没有consumer在监听，消息将被丢弃


                //定义一个交换机
                //参数1：交换机名称
                //参数2：交换机类型
                //参数3：交换机持久性，如果为true则服务器重启时不会丢失
                //参数4：交换机在不被使用时是否删除
                //参数5：交换机的其他属性
                channel.exchangeDeclare(XCHG_NAME, "fanout", true, true, null);

                while (GetInputString()) {
                    //发送一条广播消息,参数2此时无意义
                    channel.basicPublish(XCHG_NAME, "", null, message.getBytes());

                    System.out.println("Send " + message);
                }
                break;
            case DIRECT:
                //向所有绑定了相应routing key的队列发送消息
                //如果producer在发布消息时没有consumer在监听，消息将被丢弃
                //如果有多个consumer监听了相同的routing key  则他们都会受到消息

                channel.exchangeDeclare(XCHG_NAME, "direct", true, true, null);

                while (GetInputString()) {
                    //input like : info message
                    String[] temp = StringUtils.splitString(message, " ");
                    channel.basicPublish(XCHG_NAME, temp[0], null, temp[1].getBytes());
                    System.out.println("Send " + message);
                }
                break;
            case TOPIC:
                //与direct模式有类似之处，都使用routing key作为路由
                //不同之处在于direct模式只能指定固定的字符串，而topic可以指定一个字符串模式

                channel.exchangeDeclare(XCHG_NAME, "topic", true, true, null);
                while (GetInputString()) {
                    //input like : topic message
                    String[] temp = StringUtils.splitString(message, " ");
                    channel.basicPublish(XCHG_NAME, temp[0], null, temp[1].getBytes());
                    System.out.println("Send " + message);
                }
                break;
            case HEADERS:
                //与topic和direct有一定相似之处，但不是通过routing key来路由消息
                //通过headers中词的匹配来进行路由

                channel.exchangeDeclare(XCHG_NAME, "headers", true, true, null);
                while (GetInputString()) {
                    //input like : headers message
                    String[] temp = StringUtils.splitString(message, " ");

                    Map<String, Object> headers = new HashMap<>();
                    headers.put("name", temp[0]); //定义headers
                    headers.put("sex", temp[1]);
                    AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder().headers(headers);

                    channel.basicPublish(XCHG_NAME, "", builder.build(), temp[2].getBytes()); //根据headers路由到相应的consumer
                    System.out.println("Send " + message);
                }
                break;
            case RPC:
                //创建rpc回执队列
                queueName = channel.queueDeclare().getQueue();
                AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().replyTo(queueName).build();
                while (GetInputString()) {
                    channel.basicPublish("", QUEUE_NAME, props, message.getBytes());
                    System.out.println("Send " + message);

                    //接收回调
                    channel.basicQos(1);
                    QueueingConsumer consumer = new QueueingConsumer(channel);
                    channel.basicConsume(queueName, false, consumer);
                    while (true) {
                        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                        System.out.println("Received " + new String(delivery.getBody()));
                        //回复ack包，如果不回复，消息不会在服务器删除
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        //channel.basicReject(); channel.basicNack(); //可以通过这两个函数拒绝消息，可以指定消息在服务器删除还是继续投递给其他消费者
                    }
                }
                break;
        }

        //关闭频道和连接
        channel.close();
        connection.close();
    }

    private static boolean GetInputString() {
        message = scanner.nextLine();
        if (message.length() == 0) {
            return false;
        }
        return true;
    }


}
