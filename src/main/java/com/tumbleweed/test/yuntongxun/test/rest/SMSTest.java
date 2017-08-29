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
import org.json.JSONObject;
import org.junit.Test;
import com.tumbleweed.test.base.common.HttpPostUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSTest implements Runnable {

    public Logger log = LogManager.getLogger(SMSTest.class);

    // 创建模板短信
    @Test
    public void createTemplateSMSXml() throws NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("TemplateSMS");
        //应用id
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");

        //1:已发布的app 2:未发表的app  3:网页',
        root.addElement("productType").addText("1");
        //1,3地址
        root.addElement("addr").addText("D:app");

        //模板标题 title
        root.addElement("title").addText("110");
        //签名
        root.addElement("signature").addText("证大速贷");

        root.addElement("auditNotifyUrl").addText("http://baidu.com");

        //模板内容
        root.addElement("templateContent").addText("还钱～");

        String body = document.asXML();

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";

        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/CreateSMSTemplate";

        returnTT(mainAccout, token, url, body);
    }

    // 发送模板短信
    @Test
    public void TemplateSMSXML() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("TemplateSMS");
        root.addElement("to").addText("18600200156");
        root.addElement("templateId").addText("12268");

        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        Element datas = root.addElement("datas");

        datas.addElement("data").addText("wjp1");
        datas.addElement("data").addText("wjp2");
        String body = document.asXML();


        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.78:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/TemplateSMS";
        returnTT(mainAccout, token, url, body);
    }

    // 发送模板短信
    @Test
    public void TemplateSMSJSON() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        String body = "{\"to\":\"15101503489\",\"appId\":\"ff808181406ef7590140703d2d8e0000\",\"templateId\":\"1077\",\"datas\":[\"658，阿萨2\",853,\"3456\",789]}";

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/TemplateSMS";
        String s = HttpPostUtil.sendJSON(mainAccout, token, url, body);

        log.info(s);

    }

    // 发送模板短信
    @Test
    public void TemplateSMSTest() throws NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("TemplateSMS");
        root.addElement("to").addText("18838125403");
        root.addElement("templateId").addText("1195");
        root.addElement("appId").addText("ff808181406ef7590140703d2d8e0000");
        Element datas = root.addElement("datas");
        datas.addElement("data").addText("-你好-");
        String body = document.asXML();
        String mainAccout = "ff8081813fc65581013fc72b94880000";
        String token = "4e44a775d79e422a9ee26e2966d2cb66";
        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/TemplateSMS";
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
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
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();
        log.info("状态:" + status + ";\n返回包体:" + conResult);
    }


    // 发送短信
    @Test
    public void mssages() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("SMSMessage");
        root.addElement("appId").addText("297e7c37444db2f201444db340e40000");
        root.addElement("to").addText("18310536874");

        root.addElement("body").addText("18310536874");

        String body = document.asXML();


        String mainAccout = "4028eb25444d379701444d426e640001";
        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";
        String url = "http://192.168.178.90:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/Messages";
        returnTT(mainAccout, token, url, body);
    }


    // RMServer 获取短信模板
    @Test
    public void QuerySMSTemplate() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appId").addText("297e7c37444db2f201444db340e40000");
        root.addElement("templateId").addText("5633");

        root.addElement("mobile").addText("18310536874");

        String body = document.asXML();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.90:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/QuerySMSTemplate";

        returnTT("ff808181478fb4640147901ee3290003", "970a7bfc32fe45c5b54f1f4650b2ca9a", url, body);
    }

    @Test
    public void querySms() throws IOException, NoSuchAlgorithmException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("msgId").addText("109824f5db13433faf84e6d977f378a5");
        root.addElement("date").addText("20151222");
        root.addElement("phoneNum").addText("18801181688");

        String body = document.asXML();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/QuerySMS";

        returnTT(mainAccout, token, url, body);
    }

    @Test
    public void querySmsArrive() throws IOException, NoSuchAlgorithmException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("SMSArrived");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("count").addText("300");

        String body = document.asXML();

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";

        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/GetArrived";

        returnTT(mainAccout, token, url, body);
    }


    @Test
    public void querySmsArriveJson() throws IOException, NoSuchAlgorithmException {

        JSONObject json = new JSONObject();
        // 必选
        json.put("appId", "4028efe33fc65b56013fc660001f0002");
        json.put("count", "300");
        log.info(json.toString());

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";

        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/GetArrived";


        HttpPostUtil.sendJSON(mainAccout, token, url, json.toString());
    }


    @Test
    public void querySmsjson() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        // 必选
        json.put("msgId", "109824f5db13433faf84e6d977f378a5");
        json.put("date", "20151222");

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccout + "/SMS/QuerySMS";

        HttpPostUtil.sendJSON(mainAccout, token, url, json.toString());

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

    public void run() {
        try {
            Thread.sleep(60000);
            this.TemplateSMSTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runTest() {

        SMSTest smsTest = new SMSTest();
        for (int i = 0; i < 5; i++) {
            smsTest.run();
        }
    }

}

