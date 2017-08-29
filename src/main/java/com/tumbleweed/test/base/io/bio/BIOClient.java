package com.tumbleweed.test.base.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 16/02/2017.
 */
public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);
        OutputStream outputStream = client.getOutputStream();
        outputStream.write("come on baby.".getBytes());
        outputStream.close();
        client.close();
    }

}
