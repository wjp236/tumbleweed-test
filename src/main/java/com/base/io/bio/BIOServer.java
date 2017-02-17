package com.base.io.bio;

import com.base.util.SystemUtil;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 描述:bio server
 *
 * @author: mylover
 * @Time: 16/02/2017.
 */
public class BIOServer {

    //构造一个服务端
    public BIOServer(int port) {

        try {
            ServerSocket server =  new ServerSocket(port);
            SystemUtil.log("启动了 一个 bio");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listener() {

    }

    public static void main(String[] args) {

    }

}
