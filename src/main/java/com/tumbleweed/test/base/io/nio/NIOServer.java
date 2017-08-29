package com.tumbleweed.test.base.io.nio;

import com.tumbleweed.test.base.util.SystemUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 描述:nio服务器
 *
 * @author: mylover
 * @Time: 18/02/2017.
 */
public class NIOServer {

    ServerSocketChannel ssc;
    Selector selector;
    ByteBuffer receiverBuffer = ByteBuffer.allocate(1024);
    ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    Map<SelectionKey, String> sessionMsg = new HashMap<>();

    public NIOServer(int port) {
        try {
            //修建高速公路
            ssc = ServerSocketChannel.open();

            ssc.socket().bind(new InetSocketAddress(port));

            //设置为非阻塞
            ssc.configureBlocking(false);

            selector = Selector.open();

            //老板叫管家有人就来通知一下
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            SystemUtil.log("nio started");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listener() throws IOException {

        while (true) {
            int i = selector.select();
            if (i == 0) {
                continue;
            }
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterable = keySet.iterator();
            while (iterable.hasNext()) {
                //处理事情
                proess(iterable.next());

                //处理后走人
                iterable.remove();
            }
        }
    }

    private void proess(SelectionKey key) throws IOException {
        //判断客户有没有跟boss建立好连接
        if (key.isAcceptable()) {
            SocketChannel client = ssc.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);

        } else if (key.isReadable()) {
            receiverBuffer.clear();

            //判断是否可以读数据
            SocketChannel client = (SocketChannel) key.channel();
            int len = client.read(receiverBuffer);
            if (len > 0) {
                String msg = new String(receiverBuffer.array(), 0, len);

                sessionMsg.put(key, msg);

                SystemUtil.log("获取客户端发送来的消息:"+msg);
            }
            client.register(selector, SelectionKey.OP_WRITE);

        } else if (key.isWritable()) {
            if (!sessionMsg.containsKey(key)) {
                return;
            }

            //是否可以写数据
            SocketChannel client = (SocketChannel) key.channel();

            sendBuffer.clear();
            sendBuffer.put((new String(sessionMsg.get(key)) + "---well done.").getBytes());

            sendBuffer.flip();//不共用缓存区,可以不用

            //写回数据
            client.write(sendBuffer);

            //反馈后,处于待机状态
            client.register(selector, SelectionKey.OP_READ);

        }

    }

    public static void main(String[] args) throws IOException {
        new NIOServer(9999).listener();

    }

}
