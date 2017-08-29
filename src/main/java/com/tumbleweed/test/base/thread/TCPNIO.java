package com.tumbleweed.test.base.thread;

/**
 * Created by mylover on 12/10/2016.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * TCP/IP的 非阻塞模式
 */

public class TCPNIO {

    /**
     * 客户端监听事件
     * @param ipStr IP地址
     * @param portNum 端口号
     * */

    public void clientListen(String ipStr, int portNum, int blockTime)
    {
        try {
            SocketChannel channel = SocketChannel.open();

            //设置为非阻塞模式
            channel.configureBlocking(false);

            //对于非阻塞模式，立刻返回false，表示连接正在建立中
            Selector selector = Selector.open();

            //向channel注册selector和感兴趣事件
            channel.register(selector, SelectionKey.OP_CONNECT);

            //阻塞至有感兴趣的IO事件发生，或者到达超时时间，如果希望一直等待到有感兴趣的
            //事件发生，可调用无参数的select方法，如果希望不阻塞直接返回目前是否有感兴趣的
            //事件发生，可调用selectNow方法
            int nKeys = selector.select(blockTime);
            SelectionKey sKey = null;

            //nKeys大于0，说明有感兴趣的事件发生
            if(nKeys > 0)
            {
                Set<SelectionKey> keys = selector.selectedKeys();
                for(SelectionKey key:keys)
                {
                    //对于发生连接事件
                    if(key.isConnectable())
                    {
                        SocketChannel sc = (SocketChannel)key.channel();
                        sc.configureBlocking(false);

                        //注册感兴趣的IO事件，通常不直接注册写事件，在发送缓冲区未满的情况下
                        //一直是可写的，所以如果注册了写事件，而又不写数据，则很容易造成CPU消耗100%
                        sKey = sc.register(selector, SelectionKey.OP_READ);

                        //完成连接的建立
                        sc.finishConnect();
                    }
                    else if(key.isReadable())//有流可读取
                    {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        SocketChannel sc = (SocketChannel)key.channel();
                        int readBytes = 0;

                        try {
                            int ret = 0;
                            try {
                                //读取目前可读的流，sc.read(buffer)返回的是成功复制到bytebuffer中
                                //的字节数，为阻塞操作，值可能为0，若到流结尾，返回-1
                                while ((ret = sc.read(buffer)) > 0) {
                                    readBytes += ret;
                                }
                            } finally {
                                buffer.flip();
                            }
                        } finally{
                            if(buffer != null)
                            {
                                buffer.clear();
                            }
                        }
                    }
                    else if(key.isWritable())//可写入流
                    {
                        //取消对OP_WRITE事件的注册
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                        SocketChannel sc = (SocketChannel)key.channel();

                        //此步为阻塞操作，知道写入操作系统发送缓冲区或者网络IO出现异常
                        //返回的为成功写入的字节数，若缓冲区已满，返回0
                        int writeenedSize = sc.write(buffer);

                        //若未写入，继续注册感兴趣的OP_WRITE事件
                        if(writeenedSize == 0)
                        {
                            key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                        }
                    }
                }

                selector.selectedKeys().clear();
            }
			/*
			 * 也可以直接调用channel.write完成写
			 * 只有在写入未成功时才注册OP_WRITE事件
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int wSize = channel.write(buffer);
			if(wSize == 0)
			{
				key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
			}*/

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 服务器端监听事件
     * @param portNum 端口号
     * */
    public void serverListen(int portNum, int blockTime)
    {
        ServerSocketChannel ssc;
        try {
            ssc = ServerSocketChannel.open();
            ServerSocket serverSocket = ssc.socket();
            serverSocket.bind(new InetSocketAddress(portNum));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            int nKeys = selector.select(blockTime);
            if(nKeys > 0)
            {
                Set<SelectionKey> keys = selector.selectedKeys();
                for(SelectionKey key:keys)
                {
                    if(key.isAcceptable())
                    {
                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        SocketChannel sc = server.accept();
                        if(sc == null)
                        {
                            continue;
                        }
                        sc.configureBlocking(false);

                        //注册感兴趣的连接建立事件
                        sc.register(selector, SelectionKey.OP_READ);
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}