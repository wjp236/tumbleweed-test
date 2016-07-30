package com.enn.test;

import com.base.common.EncryptUtil;
import com.enn.common.HttpPostUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mylover on 4/9/16.
 */
public class WebCashEcejTest {

    public Logger log = LogManager.getLogger(WebCashEcejTest.class);

    private static final String localServerUrl = "http://localhost:8081/webCash";
    private static final String serverUrl = "http://222.222.120.75:8081/webCash-core/webCash";
    private static final String serverDevUrl = "http://10.37.148.254:9022/webCash-core/webCash";
    private static final String testServerUrl = "http://119.254.196.122:9022/webCash-core/webCash";
    private static final String ecejtestServerUrl = "http://pay.t.ecej.com/webCash-core/webCash";

    @Test
    public void makePwd() {
        String pwd = EncryptUtil.md5(EncryptUtil.md5("123456") + "123456");
        log.info(pwd);
    }

    /**
     * signature签名
     */
    @Test
    public void binding() throws IOException, NoSuchAlgorithmException {
//        Document document = DocumentHelper.createDocument();
//        Element root = document.addElement("Response");
//        root.addElement("appId").addText("ff8081813fc747ee013fc74998810001");
//        root.addElement("test").addText("test");

        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_time", "20160426163043");
        json.put("user_token", "16f71ae9eae144d2a5c22a0922480257");
        json.put("signature", "xinyiPay");

        String body = json.toString();

        String url = testServerUrl + "/xinyi/make/signature";

        log.info(url);
        HttpPostUtil.sendXml(url, body);
    }

    /**
     * 统一下单
     */
    @Test
    public void unifiedOrder() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "000000");
        json.put("req_ip", "127.0.0.1");
        json.put("biz_type", "GOODS");
        json.put("trade_amt", "1.0");
        json.put("trade_desc", "篮球");
        json.put("req_time",    "20160627062820");
        json.put("time_expire", "20160627182820");
        json.put("trade_mode", "GUARANTEEPAY");
        json.put("trade_detail", "秘制篮球");
        json.put("currency", "CNY");
        json.put("pay_type", "APP");
        json.put("merc_order_no", "20160624062822");
        json.put("attach", "userdata");
        json.put("receive_no", "8011266930001798881280");
        json.put("notify_url", "http://10.37.148.254:9022/webCash-core/webCash/xinyi/make/metCallBack");
        json.put("pay_type", "APP");
        json.put("cashAmt", "3.0");
        json.put("priAmt", "2.0");
        json.put("packAmt", "1.0");
        String body = json.toString();
        log.info(body);
        String urlsignature = testServerUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = testServerUrl + "/pay/unifiedOrder";
        HttpPostUtil.sendJSON(url, requestBody);
    }

    /**
     * 唤起收银台签名
     */
    @Test
    public void getThdSdk() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("salt","123456");
        json.put("merc_id", "8011056811254598983686");
        json.put("trade_no", "124201606271806570000011186816");
        json.put("req_time","20160428105935");
        json.put("appid", "A000001");
        json.put("tradeChannel", "WX");
        log.info(json);

        String body = json.toString();
        String url = serverUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(url, body);

        String callUrl = serverUrl + "/pay/getThdSdk";
        HttpPostUtil.sendJSON(callUrl, requestBody);
    }

    @Test
    public void account() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("salt","123456");
        json.put("merc_id", "8011056811254598983686");
        json.put("trade_no", "124201606241059440000010138240");
        json.put("req_time","20160428105935");
        String body = json.toString();
        log.info(body);
        String urlsignature = serverUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = serverUrl + "/pay/accounting";
        HttpPostUtil.sendJSON(url, requestBody);
    }

    @Test
    public void queryTrade() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("salt","123456");
        json.put("merc_id", "8011056811254598983686");
        json.put("trade_no", "124201606241401040000010138240");
        json.put("req_time","20160428105935");
        String body = json.toString();
        log.info(body);
        String urlsignature = serverUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = serverUrl + "/pay/queryOrderInfo";
        HttpPostUtil.sendJSON(url, requestBody);
    }


    /**
     * 回调接口
     */
    @Test
    public void callBack() throws IOException, NoSuchAlgorithmException {

        String url = localServerUrl + "/pay/callBack/ZFB";

        HttpPostUtil.sendXml(url, "test");
    }

    /**
     * 回调接口
     */
    @Test
    public void metCallBack() throws IOException, NoSuchAlgorithmException {

        String url = localServerUrl + "/xinyi/make/metCallBack";

        HttpPostUtil.sendJSON(url, "");
    }

    /**
     * 微信回调
     */
    @Test
    public void wxCallBack() throws DocumentException, IOException, NoSuchAlgorithmException {
        File f = new File("src/main/java/com/enn/file/wx.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(f);
        String value = document.asXML();
        log.info(value);
        String callUrl = localServerUrl + "/pay/callBack/WX";
        HttpPostUtil.sendJSON(callUrl, value);
    }

    @Test
    public void testMq() throws IOException, NoSuchAlgorithmException {

        String url = localServerUrl + "/xinyi/test/mq";

        JSONObject json = new JSONObject();
        json.put("requestId","123456");
        json.put("appId", "1234567890");
        json.put("accountDate", "20161111111111");
        json.put("currency","1");
        json.put("tradeAmount", "1.00");
        json.put("privilegePrice", "1.00");
        json.put("xinyiShell", "1.00");
        json.put("cash", "1.00");
        json.put("payChannel", "1");
        json.put("type", "1");

        HttpPostUtil.sendJSON(url, json.toString());

    }



}
