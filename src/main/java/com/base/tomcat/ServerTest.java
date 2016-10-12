package com.base.tomcat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mylover on 11/10/2016.
 */
public class ServerTest {

    private static Logger logger = LogManager.getLogger(ServerTest.class);

    private static int num = 0;

    @Test
    public void main(String[] args) {

        ServerSocket ss = null;

        Socket socket = null;

        try {

            ss = new ServerSocket(9999);

            logger.info("服务器初始化完毕");
            //阻塞式接受客户端的方法

            socket = ss.accept();

            InputStream inputStream = socket.getInputStream();

            byte[] buff = new byte[1024];

            int length = inputStream.read();

            if (length > 0) {

                String msg = new String(buff, 0, length);
                logger.info("msg---:"+msg);

            } else {
                logger.info("-1 null");
            }

            OutputStream outputStream = socket.getOutputStream();

            String msg = "come on baby";

            outputStream.write(msg.getBytes());

            outputStream.flush();

            outputStream.close();

            socket.close();

        } catch (IOException e) {
            logger.info(e);
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
