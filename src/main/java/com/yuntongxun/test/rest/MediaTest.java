package com.yuntongxun.test.rest;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.File;

/**
 * Created by mylover on 9/17/15.
 */
public class MediaTest {

    public Logger logger = LogManager.getLogger(MediaTest.class);

    @Test
    public void mediaTest() {

        File file = new File("src/yun/tong/xun/file/ConditionalLogic.mp4");

        Encoder encoder = new Encoder();

        try {
            MultimediaInfo m = encoder.getInfo(file);
            long ls = m.getDuration();
            logger.info(ls);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
