package wang.tumbleweed.test.base;

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
import wang.tumbleweed.common.Base64;
import wang.tumbleweed.common.MD5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public Logger log = LogManager.getLogger(Test.class);

    @org.junit.Test
    public void test() throws NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();

        String body = document.asXML();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.177.108:8080/2013-12-26/inter/test";
        for (int i = 0; i < 1; i++) {
//        for (int i = 0; i < 100000000; i++) {
            returnTT(mainAccout, token, url, body);
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
}
