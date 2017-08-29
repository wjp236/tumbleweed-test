package com.tumbleweed.test.yuntongxun.test.ecfile;

import com.tumbleweed.test.yuntongxun.model.FileParams;
import com.tumbleweed.test.base.test.XmlTest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;
import org.ming.sample.util.JSONUtil;
import org.ming.sample.util.StreamUtil;
import com.tumbleweed.test.base.common.EncryptUtil;
import com.tumbleweed.test.base.common.HttpPostUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mylover on 9/9/15.
 */
public class ECFileTest implements Runnable {

    public Logger logger = LogManager.getLogger(ECFileTest.class);

    @Test
    public void downAttachConfig() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();

        json.put("version", "6");
        json.put("type", "1");

        String url = "http://localhost:8080/2015-03-26/Corp/yuntongxun/Download/Attach";
        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String s = HttpPostUtil.sendJSON(mainAccount, token, url, json.toString());
        logger.info(s);
    }

    @Test
    public void generateConfig() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();

        json.put("version", "6");
        json.put("type", "1");

        String url = "http://localhost:8080/2015-03-26/Corp/yuntongxun/Generate/ServerAddrs";
        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String s = HttpPostUtil.sendJSON(mainAccount, token, url, json.toString());
        logger.info(s);
    }


    @Test
    public void uploadAttach() throws Exception {

      String url = "http://localhost:8080/2015-03-26/Corp/yuntongxun/Upload/Attach?"

//        String url = "http://123.56.149.246:8090/2015-03-26/Corp/yuntongxun/Upload/Attach?"
                + "token=pxdTest2&type=1&sig="
                + EncryptUtil.md5("yuntongxunytx123");

        DefaultHttpClient client = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/xml;");
        httpPost.setHeader("Content-Type", "application/octet-stream");

        FileParams fileParams = new FileParams("20150314000000110000000000000010","1432126079881KL91111111113879745","4","18701360647","18600668603","12345678912345678901234567890.jpg","2");

//        FileParams fileParams = new FileParams("20150314000000110000000000000010","1432126079881KL91111111113879745","4","18701360647","18600668603","hui10.png","2");

//        FileParams fileParams = new FileParams("20150314000000110000000000000010","1432126079881KL91111111113879745","3","18600200156","18210819960","ConditionalLogic.mp4","0");

//        FileParams fileParams = new FileParams("20150314000000110000000000000010","1432126079881KL91111111113879745","2","150113083041","18210819960","1343b.amr","0");

//        FileParams fileParams = new FileParams();

        String ytx_params = JSONUtil.object2json(fileParams).toString();

        logger.info(ytx_params);

        String params = EncryptUtil.base64Encoder(ytx_params);

        logger.info(params);

        BasicHttpEntity entity = null;

//        File file = new File("src/yun/tong/xun/file/ConditionalLogic.mp4");

        File file = new File("src/main/java/yun/tong/xun/file/12345678912345678901234567890.jpg");

        HttpResponse response = null;
        long startTime = System.currentTimeMillis();
        int i = 0;
        if (file.exists()) {
            InputStream is = new FileInputStream(file);
            long start = 0;
            long end = 0;

            httpPost.setHeader("ytx_params", XmlTest.replaceBlank(params));
            entity = new BasicHttpEntity();
            entity.setContent(is);
            entity.setContentLength(is.available());

            httpPost.setEntity(entity);
            response = client.execute(httpPost);

            logger.info(end - start);

            int status = response.getStatusLine().getStatusCode();
            logger.info(status + "------" +
                    StreamUtil.readContentByStream(response.getEntity().getContent()));
            EntityUtils.consume(response.getEntity());
        }

        logger.info("total send count:" + i);
        logger.info("total time:" + (System.currentTimeMillis() - startTime));

        client.getConnectionManager().shutdown();
    }

    @Test
    public void uploadVTM() throws Exception {
//        String fileName = java.net.URLEncoder.encode("测试vtm.png","UTF-8");
        String url = "http://localhost:8080/2015-03-26/Corp/yuntongxun/Upload/VTM?"
                + "token=pxdTest2&type=1&sig="
                + EncryptUtil.md5("yuntongxunytx123") + "&appId=8a48b5514e5298b9014e6730a29f12d7" +
                "&userName=laowang&fileName="+"&callbackurl=" + EncryptUtil.base64Encoder("http://www.baidu.com");

        DefaultHttpClient client = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/xml;");
        httpPost.setHeader("Content-Type", "application/octet-stream");

        BasicHttpEntity entity = null;

        File file = new File("src/yun/tong/xun/file/hui10.png");

        HttpResponse response = null;
        long startTime = System.currentTimeMillis();
        int i = 0;
        if (file.exists()) {
            InputStream is = new FileInputStream(file);
            long start = 0;
            long end = 0;
            entity = new BasicHttpEntity();
            entity.setContent(is);
            entity.setContentLength(is.available());
            httpPost.setEntity(entity);
            response = client.execute(httpPost);
            logger.info(end - start);
            int status = response.getStatusLine().getStatusCode();
            logger.info(status + "------" +
                    StreamUtil.readContentByStream(response.getEntity().getContent()));
            EntityUtils.consume(response.getEntity());
        }
        logger.info("total send count:" + i);
        logger.info("total time:" + (System.currentTimeMillis() - startTime));

        client.getConnectionManager().shutdown();
    }

    public void run() {
        try {
            this.uploadAttach();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRun() {
        for (int i = 0; i < 1000; i++) {
            run();
        }
    }
}
