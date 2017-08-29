package com.tumbleweed.test.base.io.bio;

import com.tumbleweed.test.base.util.SystemUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述:bio server
 *
 * @author: mylover
 * @Time: 16/02/2017.
 */
public class BIOServer {

    ServerSocket server;

    //构造一个服务端
    public BIOServer(int port) {

        try {
            server =  new ServerSocket(port);
            SystemUtil.log("启动了 一个 bio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //监听客户端过来的请求
    public void listener() throws IOException {
        while (true) {
            Socket socket = server.accept();
            InputStream is = socket.getInputStream();
            byte[] buff = new byte[1024];
            int len = is.read(buff);
            if (len > 0) {
                String msg = new String(buff, 0, len);
                SystemUtil.log("接收客户端的消息:" + msg);
            }
            is.close();
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOServer(9999).listener();
    }

}
