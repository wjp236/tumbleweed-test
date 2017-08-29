package com.tumbleweed.test.yuntongxun.test.rest;

import org.apache.http.client.ClientProtocolException;
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

/**
 * Created by mylover on 8/11/15.
 */
public class ConferenceTest {

    public Logger log = LogManager.getLogger(ConferenceTest.class);


    // 创建会议
    @SuppressWarnings("static-access")
    @Test
    public void testConfCreate() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement("Request");
        root.addElement("Appid").addText("4028efe33fc65b56013fc660001f0002");

        Element createCalls = root.addElement("CreateConf");
        createCalls.addElement("action").addText("createconfresult.jsp");// 成功回调
        createCalls.addElement("maxmember").addText("10");

        String body = document.asXML();

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccout + "/ivr/createconf";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String s = HttpPostUtil.sendXML(mainAccout, token, url, body);
        log.info(s);
    }

    // 创建会议
    @SuppressWarnings("static-access")
    @Test
    public void testIvrConf() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement("Request");
        root.addElement("Appid").addText("4028efe33fc65b56013fc660001f0002");

        Element createCalls = root.addElement("InviteJoinConf");
        createCalls.addAttribute("confid", "10002644");
        createCalls.addAttribute("number", "18210819960#18600200156");

        String body = document.asXML();

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccout + "/ivr/createconf";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String s = HttpPostUtil.sendXML(mainAccout, token, url, body);
        log.info(s);
    }

    @Test
    public void changeState() throws IOException, NoSuchAlgorithmException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("roomId").addText("1004123DF");
        root.addElement("type").addText("1");
        root.addElement("state").addText("10");
        root.addElement("mobile").addText("1001#8002");
        root.addElement("useracctype").addText("2");
        root.addElement("userName").addText("1001#8001");
        root.addElement("userdata").addText("{123214}");

        String subAccout = "4028efe33fc65b56013fc660004a0003";

        String token = "72cc0014d5584e42a3db9de3847eb9dd";

        String url = "http://localhost:8080/2013-12-26/SubAccounts/" + subAccout + "/inter/conf/ChangeMemberState";

        HttpPostUtil.sendXML(subAccout, token, url, document.asXML());

    }


    @Test
    public void changeStatejson() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        // 必选
        json.put("appId", "4028efe33fc65b56013fc660001f0002");

        String subAccout = "4028efe33fc65b56013fc660004a0003";

        String token = "72cc0014d5584e42a3db9de3847eb9dd";

        String url = "http://localhost:8080/2013-12-26/SubAccounts/" + subAccout + "/inter/conf/ChangeMemberState";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        HttpPostUtil.sendJSON(subAccout, token, url, json.toString());

    }

    @Test
    public void intercomCreate() throws IOException, NoSuchAlgorithmException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        Element members = root.addElement("members");
        for (int i = 0; i <= 3; i++) {
            members.addElement("member").addText(i + "");
        }

        String subAccout = "4028efe33fc65b56013fc660004a0003";

        String token = "72cc0014d5584e42a3db9de3847eb9dd";

        String url = "http://localhost:8080/2013-12-26/SubAccounts/" + subAccout + "/inter/interphone/create";

        HttpPostUtil.sendXML(subAccout, token, url, document.asXML());
    }

    @Test
    public void createChatRoom() throws IOException, NoSuchAlgorithmException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("ChatRoom");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("square").addText("2");
        root.addElement("roomName").addText("'hui10");


        String subAccout = "4028efe33fc65b56013fc660004a0003";

        String token = "72cc0014d5584e42a3db9de3847eb9dd";

        String url = "http://localhost:8080/2013-12-26/SubAccounts/" + subAccout + "/inter/chatroom/create";

        HttpPostUtil.sendXML(subAccout, token, url, document.asXML());
    }


}
