package com.tumbleweed.test.yuntongxun.test.rest;

import com.tumbleweed.test.yuntongxun.model.MultiPartyCallForm;
import com.tumbleweed.test.yuntongxun.model.MultiPartyCallMemberListForm;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mylover on 8/11/15.
 */
public class PartnerTest {

    public Logger log = LogManager.getLogger(PartnerTest.class);

    @SuppressWarnings("static-access")
    @Test
    public void BoundAccountRate() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        String mainAccout = "402832014c0bf201014c0bffd4090000";
        String token = "77963b7a931377ad4ab5ad6a9cd718aa";

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("accountSid").addText(
                "4028eb25444d379701444d426e640001");
        root.addElement("rateId").addText("1");
        String body = document.asXML();

        String url = "http://192.168.178.219:8080/2013-12-26/Channel/"
                + mainAccout + "/partner/BoundAccountRate";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(mainAccout, token, url, body);

        System.out.println(s);

    }


    // 腾讯创建多方通话
    @SuppressWarnings("static-access")
    @Test
    public void testConfTencent() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement("MultiPartyCall");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("maxMember").addText("4");
        Element createCalls = root.addElement("members");
        createCalls.addElement("number").addText("18600200156");
        createCalls.addElement("number").addText("18210819960");
        String body = document.asXML();

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccout + "/Calls/MultiPartyCall";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String s = HttpPostUtil.sendXML(mainAccout, token, url, body);
        log.info(s);
    }

    // 腾讯创建多方通话
    @SuppressWarnings("static-access")
    @Test
    public void testConfTencentJson() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        /*
         * Map<String,Object> map = new HashMap<String,Object>();
		 * map.put("appId", "4028efe33fc65b56013fc660001f0002");
		 * map.put("maxMember", "4"); List<Map<String,Object>> numbers = new
		 * ArrayList<Map<String,Object>>(); Map<String,Object> numberMap1 = new
		 * HashMap<String,Object>(); numberMap1.put("number","18600200156");
		 * numbers.add(numberMap1);
		 *
		 * Map<String,Object> numberMap2 = new HashMap<String,Object>();
		 * numberMap2.put("number","18210819960"); numbers.add(numberMap2);
		 */

        // map.put("members", numbers);

        MultiPartyCallForm form = new MultiPartyCallForm();
        form.setAppId("4028efe33fc65b56013fc660001f0002");
        form.setMaxMember("4");

        MultiPartyCallMemberListForm m1 = new MultiPartyCallMemberListForm();


        List<String> members = new ArrayList<String>();
        members.add("18600200156");
        members.add("18210819960");

        m1.setNumber(members);


        net.sf.json.JSONObject json = new net.sf.json.JSONObject();
        json = net.sf.json.JSONObject.fromObject(form);

        String body = json.toString();

        System.err.println("body=" + body);

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";

        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccount + "/Calls/MultiPartyCall";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendJSON(mainAccount, token, url, body);

        log.info("----------wjp-----" + s);

    }

    // 号码验证
    @SuppressWarnings("static-access")
    @Test
    public void testNumVerify() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement("NumVerify");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("to").addText("186002001561");
        root.addElement("respUrl").addText("www.baidu.com");
        root.addElement("verifyCode").addText("123456");
        root.addElement("playTimes").addText("3");
        root.addElement("displayNum").addText("01052823293");
        root.addElement("userData").addText("666666");
        root.addElement("callStateUrl").addText("www.baidu.com/2013-12-26/inner/partner/NumVerify/status");
        root.addElement("hangupCdrUrl").addText("www.baidu.com/2013-12-26/inner/partner/NumVerify/Cdr");

        String body = document.asXML();

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccout + "/partner/NumVerify";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String s = HttpPostUtil.sendXML(mainAccout, token, url, body);
        log.info(s);
    }

    // 号码验证
    @SuppressWarnings("static-access")
    @Test
    public void testNumVerifyJson() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();

        JSONObject json = new JSONObject();
        // 必选
        json.put("appId", "4028efe33fc65b56013fc660001f0002");
        json.put("to", "186002001561");
        json.put("respUrl","www.baidu.com");
        // 可选
        json.put("verifyCode", "222222");
        json.put("playTimes", "2");
        json.put("respUrl", "http://192.168.178.219:8080/ivrDialVoice/wjp");
        json.put("displayNum", "01057234444");
        json.put("userData", "personal Data");
//        json.put("callStateUrl","http://192.168.178.219:8080/ivrDialVoice/wjp/state");
        json.put("hangupCdrUrl","http://192.168.178.219:8080/ivrDialVoice/wjp/cdr");

        String body = json.toString();

        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccout + "/partner/NumVerify";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String s = HttpPostUtil.sendJSON(mainAccout, token, url, body);
        log.info(s);
    }

    /**
     * 获取话单接口
     *
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void getCdr() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");

        root.addElement("callId").addText("160115094848497400010006000021ed");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/"+mainAccount+"/GetCdr";// 本地

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        HttpPostUtil.sendXML(mainAccount, token, url, body);
    }


    /**
     *创建路由
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void createRoute() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");

        root.addElement("caller").addText("8000000001");
        root.addElement("called").addText("8000000002");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("asrurl").addText("http://0.0.0.0");
        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/"+mainAccount+"/CommitRoute";// 本地

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        HttpPostUtil.sendXML(mainAccount, token, url, body);
    }


    /**
     *创建路由
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void updateRoute() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");

        root.addElement("caller").addText("8000000001");
        root.addElement("called").addText("8000000002");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("asrurl").addText("http://1.1.1.1");
        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/"+mainAccount+"/ModifyRoute";// 本地

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        HttpPostUtil.sendXML(mainAccount, token, url, body);
    }

    /**
     *创建路由
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void RemoveRoute() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");

        root.addElement("caller").addText("8000000001");
        root.addElement("called").addText("8000000002");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("asrurl").addText("http://1.1.1.1");
        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Accounts/"+mainAccount+"/RemoveRoute";// 本地

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        HttpPostUtil.sendXML(mainAccount, token, url, body);
    }


    //
    @SuppressWarnings("static-access")
    @Test
    public void CheckNumber() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("CheckNumber");

        root.addElement("appId").addText("4028eb25456387a00145638c268c0003");
        root.addElement("to").addText("18600200156");
        root.addElement("mediaTxt").addText("'304‘");
        root.addElement("displayNum").addText("123456");
        root.addElement("respUrl").addText("http://www.sohu.com/abc");

        String body = document.asXML();
        String mainAccount = "4028eb25456387a00145638a1b6b0000";
        String token = "7827000cca644324b00ee30291f0a441";
        String url = "http://192.168.21.56:8881/2013-12-26/Accounts/" + mainAccount + "/Calls/CheckNumber";
//        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccount + "/Calls/CheckNumber";
        String s = HttpPostUtil.sendXML(mainAccount, token, url, body);

        log.info(s);
    }


    // 营销外呼 callState
    @SuppressWarnings("static-access")
    @Test
    public void CheckNumberState() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("LandingCall");

        root.addElement("callSid").addText("16012615042692190001000400004668");
        root.addElement("action").addText("SellingCall");
        root.addElement("number").addText("'18210198380‘");
        root.addElement("state").addText("1");
        root.addElement("duration").addText("2");
        root.addElement("userData").addText("3");

        String body = document.asXML();
        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://localhost:8080/2013-12-26/inner/Calls/CheckNumber/CallState";

        HttpPostUtil.sendXML(mainAccount, token, url, body);

    }
}
