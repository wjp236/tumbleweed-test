package com.tumbleweed.test.yuntongxun.test.rest;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import com.tumbleweed.test.base.common.HttpPostUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class QueueTest {

    public Logger log = LogManager.getLogger(QueueTest.class);

    /**
     * 创建队列
     */
    @Test
    public void CreateQueue() {

        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("Appid").addText("297e7c37444db2f201444db340e40000");
        root.addElement("CreateQueue").addAttribute("queuetype", "311");

        String body = document.asXML();

        String channelAccount = "4028eb25444d379701444d426e640001";
        String channelToken = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.90:8080/2013-12-26/Accounts/" + channelAccount + "/ivr/createqueue";

        HttpPostUtil httppostutil = new HttpPostUtil();

        try {
            String result = httppostutil.sendXML(channelAccount, channelToken, url, body);
            System.out.println(result);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 修改队列
     */
    @Test
    public void modifyqueue() {

        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("Appid").addText("297e7c37444db2f201444db340e40000");
        root.addElement("ModifyQueue").addAttribute("queuetype", "311").addAttribute("offworktalk", "1");

        String body = document.asXML();

        String channelAccount = "4028eb25444d379701444d426e640001";
        String channelToken = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.90:8080/2013-12-26/Accounts/" + channelAccount + "/ivr/modifyqueue";

        HttpPostUtil httppostutil = new HttpPostUtil();

        try {
            String result = httppostutil.sendXML(channelAccount, channelToken, url, body);
            System.out.println(result);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Test
    public void delqueue() {

        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("Appid").addText("aaf98f89429833490142983c5c480001");
        root.addElement("DelQueue").addAttribute("queuetype", "3");

        String body = document.asXML();

        String channelAccount = "aaf98fda425ae340014268f01ff9004b";
        String channelToken = "52677a185b8a40aaacc4b597474f4aa9";

        String url = "http://app.cloopen.com:8881/2013-12-26/Accounts/" + channelAccount + "/ivr/delqueue";

        HttpPostUtil httppostutil = new HttpPostUtil();

        try {
            String result = httppostutil.sendXML(channelAccount, channelToken, url, body);
            System.out.println(result);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
