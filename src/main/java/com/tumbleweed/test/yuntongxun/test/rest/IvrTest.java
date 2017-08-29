package com.tumbleweed.test.yuntongxun.test.rest;

import com.tumbleweed.test.base.common.Base64;
import com.tumbleweed.test.base.common.MD5;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mylover on 8/11/15.
 */
public class IvrTest {

    public Logger log = LogManager.getLogger(IvrTest.class);


    // ivr外呼
    @Test
    public void dial() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("Appid").addText("297e7c37444db2f201444db340e40000");
        root.addElement("Dial ").addAttribute("number", "18310536874");

        String body = document.asXML();


        String mainAccout = "4028eb25444d379701444d426e640001";
        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";
        String url = "http://192.168.178.90:8080/2013-12-26/Accounts/" + mainAccout + "/ivr/dial";
        returnTT(mainAccout, token, url, body);
    }


    public void returnTT(String mainAccout, String token, String url,
                         String body) throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {
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

        log.info("状态:" + status + ";返回包体:" + conResult);

    }

}
