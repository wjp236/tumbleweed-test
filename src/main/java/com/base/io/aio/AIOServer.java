package com.base.io.aio;

import com.base.util.SystemUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * 描述:aio server
 *
 * @author: mylover
 * @Time: 19/02/2017.
 */
public class AIOServer {

    AsynchronousServerSocketChannel channel;
    ByteBuffer receviceBuff = ByteBuffer.allocate(1024);
    int port = 9999;

    public AIOServer(int port) throws IOException {
        this.port = port;

        //路修好了
        channel = AsynchronousServerSocketChannel.open();

        channel.bind(new InetSocketAddress(port));

    }

    public void listener() {
        new Thread() {
            public void run() {
                SystemUtil.log("aio started");
                channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                    @Override
                    public void completed(AsynchronousSocketChannel client, Object attachment) {
                        channel.accept(null, this);
                        process(client);
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("异步io失败");
                    }

                    private void process(AsynchronousSocketChannel client) {
                        receviceBuff.clear();
                        try {
                            int len = client.read(receviceBuff).get();
                            receviceBuff.flip();
                            System.out.println("come on baby:" + new String(receviceBuff.array(), 0, len));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
                });
                while (true){}
            }
        }.run();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new AIOServer(9999).listener();
        Thread.sleep(20000);
    }

}
