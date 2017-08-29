package com.tumbleweed.test.yuntongxun.test.rest;

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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InnerTest {

    public Logger log = LogManager.getLogger(InnerTest.class);

    //切换db
    @Test
    public void dbSwitch() throws NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("SwitchDB");
        root.addElement("seq").addText("20140916142030UA890S90");
        root.addElement("type").addText("0");
        root.addElement("sign").addText("34f93632174b8286e8ba3f8a82215559");

        String body = document.asXML();

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";

        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/inner/switchDB";

        returnTT(mainAccout, token, url, body);
    }

    public void returnTT(String mainAccout, String token, String url,
                         String body) throws NoSuchAlgorithmException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        log.info(sig);
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
        String conResult = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();

        log.info("状态:" + status + ";\n返回包体:" + conResult);

    }


}

