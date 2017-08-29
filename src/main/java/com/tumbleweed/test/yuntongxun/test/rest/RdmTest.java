package com.tumbleweed.test.yuntongxun.test.rest;

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


public class RdmTest {

    public Logger log = LogManager.getLogger(RdmTest.class);

    @Test
    public void rdmLoad() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        String httpurl = "http://192.168.21.51:8860/DataSync";
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("action").addText("UPDATE");
        root.addElement("type").addText("SQL_ID");
        root.addElement("sql_key").addText("200001");
        root.addElement("sql_cond").addText("1606785f948411e48c140050568e62f2");

        String body = document.asXML();

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);
        httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
        httppost.setHeader("Accept", "application/xml");
        HttpEntity entity = new StringEntity(body, "UTF-8");
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();
        log.info("状态:" + status + ";返回包体:" + conResult);

    }


}
