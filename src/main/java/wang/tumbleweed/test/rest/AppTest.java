package wang.tumbleweed.test.rest;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import wang.tumbleweed.common.HttpPostUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AppTest {

    public Logger log = LogManager.getLogger(AppTest.class);

    // add应用信息
    @SuppressWarnings("static-access")
    @Test
    public void addApp() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appType").addText("2");
        root.addElement("appName").addText("王建平应用");
        root.addElement("industry").addText("1");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/" + accountSid + "/CreateApplications";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        log.info(s);
    }

    // add应用信息
    @SuppressWarnings("static-access")
    @Test
    public void queryApp() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/" + accountSid + "/QueryApplication";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        log.info(s);
    }


    // 修改应用信息
    @SuppressWarnings("static-access")
    @Test
    public void modifyApp() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("appName").addText("仲召霞应用1");
        root.addElement("industry").addText("1");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/" + accountSid + "/ModifyApplication";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        log.info(s);
    }

    // 修改应用状态
    @SuppressWarnings("static-access")
    @Test
    public void modifyAppStatus() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("option").addText("1");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/" + accountSid + "/ApplyAppStatus";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        log.info(s);
    }

    // 私有云应用
    @Test
    public void saveApp() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appName").addText("laowang");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/Cloud50/Application/CreateApplication";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        log.info(s);
    }


}
