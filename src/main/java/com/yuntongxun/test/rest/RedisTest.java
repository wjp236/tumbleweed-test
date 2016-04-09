package com.yuntongxun.test.rest;

import com.base.common.Base64;
import com.base.common.MD5;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisTest implements Runnable {

    public Logger log = LogManager.getLogger(RedisTest.class);


    @Test
    public void redisTest() throws NoSuchAlgorithmException, IOException {

       for (int i = 0; i < 500; i++) {
           RedisTest redisTest = new RedisTest();
           redisTest.run();
       }
    }

    @Test
    public void redis() throws NoSuchAlgorithmException, IOException {

        ExecutorService pool = Executors.newFixedThreadPool(500);

        pool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                Document document = DocumentHelper.createDocument();

                String body = document.asXML();

                String mainAccout = "4028eb25444d379701444d426e640001";

                String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

                String url = "http://192.168.178.219:8080/2013-12-26/inter/Test/redis";

                returnTT(mainAccout, token, url, body);

                return null;
            }
        });

        pool.shutdown();

    }


    public void returnTT(String mainAccout, String token, String url,
                         String body) throws NoSuchAlgorithmException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        String httpurl = url + "?sig=" + sig;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);
        httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
        httppost.setHeader("Accept", "application/xml");
        httppost.setHeader("Authorization", authorization);

        HttpEntity entity = new StringEntity(body, "UTF-8");
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();

        log.info("状态:" + status + ";\n返回包体:" + conResult);

    }

    @Override
    public void run() {
        Document document = DocumentHelper.createDocument();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.219:8080/2013-12-26/inter/Test/redis";

        try {
            returnTT(mainAccout, token, url, "");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
