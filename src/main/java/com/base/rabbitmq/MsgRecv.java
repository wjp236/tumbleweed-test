package com.base.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mylover on 29/11/2016.
 */
public class MsgRecv {

    private final static String XCHG_NAME = "xchg_20161029";

    private final static String QUEUE_NAME = "test_queue_20161029";
    private final static String USER_PASS = "developer";
    private final static String HOST = "10.37.148.195";
    private final static int POST = 5672;

    public enum XT {
        DEFAULT, DIRECT, TOPIC, HEADERS, FANOUT, RPC
    }

    @Test
    public void main() throws Exception {
        //打开连接和创建频道，与发送端一样
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost(HOST);
        factory.setPort(POST);

        factory.setUsername(USER_PASS);
        factory.setPassword(USER_PASS);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = QUEUE_NAME;

        XT xt = XT.RPC;

        switch (xt) {
            case DEFAULT:
                //队列的相关参数需要与第一次定义该队列时相同，否则会出错，使用channel.queueDeclarePassive()可只被动绑定已有队列，而不创建
                channel.queueDeclare(queueName, false, false, true, null);
                break;
            case FANOUT:
                //channel.exchangeDeclarePassive() 可以使用该函数使用一个已经建立的exchange
                //接收端也声明一个fanout交换机
                channel.exchangeDeclare(XCHG_NAME, "fanout", true, true, null);

                //声明一个临时队列，该队列会在使用完比后自动销毁
                queueName = channel.queueDeclare().getQueue();

                //声明持久化队列
                //channel.queueDeclare(queueName, true, false, true, null);

                //将队列绑定到交换机,参数3无意义此时
                channel.queueBind(queueName, MsgSend.XCHG_NAME, "");
                break;
            case DIRECT:
                channel.exchangeDeclare(XCHG_NAME, "direct", true, true, null);
                queueName = channel.queueDeclare().getQueue();
                channel.queueBind(queueName, XCHG_NAME, "info"); //绑定一个routing key，可以绑定多个
                channel.queueBind(queueName, XCHG_NAME, "warning");
                break;
            case TOPIC:
                channel.exchangeDeclare(XCHG_NAME, "topic", true, true, null);
                queueName = channel.queueDeclare().getQueue();
                channel.queueBind(queueName, XCHG_NAME, "warning.#"); //监听两种模式 #匹配一个或多个单词 *匹配一个单词
                channel.queueBind(queueName, XCHG_NAME, "*.blue");
                break;
            case HEADERS:
                channel.exchangeDeclare(XCHG_NAME, "headers", true, true, null);
                queueName = channel.queueDeclare().getQueue();
                Map<String, Object> headers = new HashMap<String, Object>() {{
                    put("name", "test");
                    put("sex", "male");
                    put("x-match", "any");//all==匹配所有条件，any==匹配任意条件
                }};
                channel.queueBind(queueName, XCHG_NAME, "", headers);
                break;
            case RPC:
                channel.queueDeclare(QUEUE_NAME, false, false, true, null);
                break;
        }

        // 在同一时间不要给一个worker一个以上的消息。
        // 不要将一个新的消息分发给worker知道它处理完了并且返回了前一个消息的通知标志（acknowledged）
        // 替代的，消息将会分发给下一个不忙的worker。
        channel.basicQos(1); //server push消息时的队列长度

        //用来缓存服务器推送过来的消息
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //为channel声明一个consumer，服务器会推送消息
        //参数1:队列名称
        //参数2：是否发送ack包，不发送ack消息会持续在服务端保存，直到收到ack。  可以通过channel.basicAck手动回复ack
        //参数3：消费者
        channel.basicConsume(queueName, false, consumer);
//        channel.basicGet(queueName, true); //使用该函数主动去服务器检索是否有新消息，而不是等待服务器推送

        switch (xt) {
            case RPC:
                while (true) {
                    QueueingConsumer.Delivery delivery = consumer.nextDelivery();

                    AMQP.BasicProperties props = delivery.getProperties();
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                            .correlationId(props.getCorrelationId()).build();
                    String message = new String(delivery.getBody());
                    int n = Integer.parseInt(message);
                    System.out.println("Received " + new String(delivery.getBody()));
                    String response = "" + fib(n);
                    channel.basicPublish("", props.getReplyTo(), replyProps, response.getBytes());
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            default: {
                while (true) {
                    QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                    System.out.println("Received " + new String(delivery.getBody()));

                    //回复ack包，如果不回复，消息不会在服务器删除
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    //channel.basicReject(); channel.basicNack(); //可以通过这两个函数拒绝消息，可以指定消息在服务器删除还是继续投递给其他消费者
                }
            }
        }
    }

    private static int fib(int n) throws Exception {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }

}
