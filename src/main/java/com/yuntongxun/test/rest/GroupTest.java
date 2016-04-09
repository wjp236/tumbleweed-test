package com.yuntongxun.test.rest;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import com.base.common.HttpPostUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class GroupTest {

    public Logger log = LogManager.getLogger(GroupTest.class);

    /**
     * 创建群组
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    @Test
    public void CreateGroup() throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {
        String subAccountSid = "588932dae25111e494400050568e55bd";
        String subAccountSidToken = "da17b77c65dcf151909385f496f258f9";
        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("name").addText("baohj");
        root.addElement("type").addText("0");
        root.addElement("permission").addText("0");

        String body = document.asXML();

        String url = "http://192.168.178.90:8080/2013-12-26/SubAccounts/" + subAccountSid + "/Group/CreateGroup";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String sfs = HttpPostUtil.sendXML(subAccountSid, subAccountSidToken, url, body);

        log.info(sfs);
    }

    /**
     * 创建群组
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    @SuppressWarnings("static-access")
    @Test
    public void JoinGroup2() throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {
        String subAccountSid = "588932dae25111e494400050568e55bd";
        String subAccountSidToken = "da17b77c65dcf151909385f496f258f9";
        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("name").addText("baohj");
        root.addElement("type").addText("0");
        root.addElement("permission").addText("0");

        String body = document.asXML();

        String url = "http://192.168.178.219:8080/2013-12-26/SubAccounts/" + subAccountSid + "/Group/JoinGroup";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String sfs = HttpPostUtil.sendXML(subAccountSid, subAccountSidToken, url, body);

        log.info(sfs);
    }

    @Test
    public void sdf() {
        String value = "-2";
        double accountFee = value.length() > 0 ? Double.parseDouble(value) : -1;

        System.out.println(accountFee);
    }
}
