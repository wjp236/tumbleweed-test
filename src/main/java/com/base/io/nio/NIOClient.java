package com.base.io.nio;

import com.base.util.SystemUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * 描述:开路,放水
 *
 * @author: mylover
 * @Time: 16/02/2017.
 */
public class NIOClient {

    SocketChannel client;
    InetSocketAddress socketAddress = new InetSocketAddress(9999);
    Selector selector;
    ByteBuffer receiverBuffer = ByteBuffer.allocate(1024);
    ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    public NIOClient() throws IOException {

        client = SocketChannel.open();
        client.configureBlocking(false);
        client.connect(socketAddress);

        selector = Selector.open();

        client.register(selector, SelectionKey.OP_CONNECT);

    }

    public void session() throws IOException {
        //判断是否已经连接了
        if (client.isConnectionPending()) {
            client.finishConnect();
            SystemUtil.log("请在控制台登记姓名:");
            client.register(selector, SelectionKey.OP_WRITE);
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if ("".equals(name)) {
                continue;
            }
            process(name);
        }

    }

    private void process(String name) throws IOException {

        boolean unFinish = true;

        //轮询
        while (unFinish) {
            int i = selector.select();
            if (i == 0) {
                continue;
            }
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isWritable()) {

                    sendBuffer.clear();
                    sendBuffer.put(name.getBytes());
                    sendBuffer.flip();

                    client.write(sendBuffer);
                    client.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    receiverBuffer.clear();
                    int len = client.read(receiverBuffer);
                    if (len > 0) {
                        receiverBuffer.flip();
                        SystemUtil.log("获取到服务端返回的消息:" + new String(receiverBuffer.array(), 0 , len));
                    }
                    client.register(selector, SelectionKey.OP_WRITE);
                    unFinish = false;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new NIOClient().session();
    }

}
