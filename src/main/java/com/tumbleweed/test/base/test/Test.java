package com.tumbleweed.test.base.test;

import com.tumbleweed.test.base.common.Base64;
import com.tumbleweed.test.base.common.MD5;
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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public Logger log = LogManager.getLogger(Test.class);

    @org.junit.Test
    public void test() throws NoSuchAlgorithmException, IOException {
        String test = null;
        long t1 = System.currentTimeMillis();
        log.info("t1:{}", t1);
        for (long i = 0; i < 100000; i++) {
            if (1 == 1) {
               log.info(i);
            }
//             hui10 += i;
        }
        log.info(System.currentTimeMillis() - t1);
    }

    @org.junit.Test
    public void test2() {
        TestInteger testInteger = new TestInteger();
        testInteger.setI(2);

        int i = testInteger.getI();
        if (i == 2) {
            log.info("true");
        }
    }

    @org.junit.Test
    public void test1() throws NoSuchAlgorithmException {
        String mainAccout = "";
        String token = "";
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        log.info(curr_date);
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        log.info("sig {}",sig);

        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        log.info("authorization {}",authorization);

    }

    public void returnTT(String mainAccout, String token, String url,
                         String body) throws NoSuchAlgorithmException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        String httpurl = url + "?status=200&message=607&time=0";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);
        httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
        httppost.setHeader("Accept", "application/xml");
        httppost.setHeader("Authorization", authorization);

        HttpEntity entity = new StringEntity(body, "UTF-8");
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        conResult = new String(conResult.getBytes("ISO-8859-1"),"UTF-8");
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();
        log.info("状态:" + status + ";\n返回包体:" + conResult);
    }

}

class TestInteger {
    private Integer i;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }
}