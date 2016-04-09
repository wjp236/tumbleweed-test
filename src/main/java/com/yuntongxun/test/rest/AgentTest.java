package com.yuntongxun.test.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import com.base.common.HttpPostUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mylover on 9/16/15.
 */
public class AgentTest {

    private static final Logger logger = LogManager.getLogger(AgentTest.class);

    @Test
    public void createQueueTest() throws IOException, NoSuchAlgorithmException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("Appid").addText("4028efe33fc65b56013fc660001f0002");

        Element queue = root.addElement("CreateQueue");
        queue.addAttribute("queuetype", "1");
        queue.addAttribute("typedes", "1type");
        queue.addAttribute("worktime", "08:00-21:00");
        queue.addAttribute("offworkdate", "2014-10-01");
        queue.addAttribute("offworkprompt", "worktimetip.wav");
        queue.addAttribute("offworkweekday", "Sun#Sat");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/" + accountSid + "/ivr/createqueue";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        logger.info(s);
    }

    @Test
    public void delQueueTest() throws IOException, NoSuchAlgorithmException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("Appid").addText("4028efe33fc65b56013fc660001f0002");

        Element queue = root.addElement("DelQueue");
        queue.addAttribute("queuetype", "1");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/" + accountSid + "/ivr/delqueue";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        logger.info(s);
    }

    @Test
    public void modifyQueueTest() throws IOException, NoSuchAlgorithmException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("Appid").addText("4028efe33fc65b56013fc660001f0002");

        Element queue = root.addElement("ModifyQueue");
        queue.addAttribute("queuetype", "1");
        queue.addAttribute("typedes", "modifytype");
        queue.addAttribute("worktime", "08:00-21:00");
        queue.addAttribute("offworkdate", "2014-10-01");
        queue.addAttribute("offworkprompt", "worktimetip.wav");
        queue.addAttribute("offworkweekday", "Sun#Sat");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/" + accountSid + "/ivr/modifyqueue";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        logger.info(s);
    }

}
