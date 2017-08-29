package com.tumbleweed.test.base.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.*;

/**
 * Created by mylover on 10/30/15.
 */
public class FileTest {

    public Logger log = LogManager.getLogger(FileTest.class);

    @Test
    public void test11() throws IOException {
        File file = new File("src/main/java/com/base/file/hui10.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        StringBuffer body = new StringBuffer();
        while((lineTxt = bufferedReader.readLine()) != null){
            body.append(lineTxt);
        }
        log.info("\n\nbody:" + body + "\n");
        read.close();
    }

}
