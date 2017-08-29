package com.tumbleweed.test.base.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.Future;

/**
 * 描述:aio client
 *
 * @author: mylover
 * @Time: 19/02/2017.
 */
public class AIOClient {

    AsynchronousSocketChannel client;
    InetSocketAddress serverAddress = new InetSocketAddress(9999);

    ByteBuffer sendBuff = ByteBuffer.allocate(1024);

    public AIOClient() throws IOException {
        client = AsynchronousSocketChannel.open();
        Future<?> f = client.connect(serverAddress);
        System.out.println("连接服务端");
    }

    public void send(String content) {
        sendBuff.clear();
        sendBuff.put(content.getBytes());
        sendBuff.flip();
        client.write(sendBuff);
    }

    public static void main(String[] args) throws IOException {
        AIOClient client = new AIOClient();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String content = sc.nextLine();
            client.send(content);
        }
    }

}
