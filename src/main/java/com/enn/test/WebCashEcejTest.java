package com.enn.test;

import com.base.common.EncryptUtil;
import com.enn.common.HttpPostUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mylover on 4/9/16.
 */
public class WebCashEcejTest {

    public Logger log = LogManager.getLogger(WebCashEcejTest.class);

    private static final String localServerUrl = "http://localhost:8080/webCash";
    private static final String serverUrl = "http://10.37.148.254:9022/webCash-core/webCash";

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
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Response");
        root.addElement("appId").addText("ff8081813fc747ee013fc74998810001");
        root.addElement("test").addText("test");

        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_time", "20160426163043");
        json.put("user_token", "16f71ae9eae144d2a5c22a0922480257");
        json.put("signature", "xinyiPay");

//        String body = document.asXML();
        String body = json.toString();

        String url = localServerUrl + "/xinyi/make/signature";

        log.info(url);
        HttpPostUtil.sendJSON(url, body);
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
        json.put("req_time", "20160621102820");
        json.put("time_expire", "20160623102820");
        json.put("trade_mode", "GUARANTEEPAY");
        json.put("trade_detail", "秘制篮球");
        json.put("currency", "CNY");
        json.put("pay_type", "APP");
        json.put("merc_order_no", "test0010");
        json.put("attach", "userdata");
        json.put("receive_no", "1234567890");
        json.put("notify_url", "http://10.37.148.254:9022/webCash-core/webCash/xinyi/make/metCallBack");
        json.put("pay_type", "APP");
        json.put("cashAmt", "3.0");
        json.put("priAmt", "2.0");
        json.put("packAmt", "1.0");
        String body = json.toString();
        log.info(body);
        String urlsignature = serverUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = localServerUrl + "/pay/unifiedOrder";
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
        json.put("trade_no", "124201606201810460000012103296");
        json.put("req_time","20160428105935");
        json.put("appid", "A000001");
        json.put("tradeChannel", "CUP");
        log.info(json);

        String body = json.toString();
        String url = serverUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(url, body);

        String callUrl = serverUrl + "/pay/getThdSdk";
        HttpPostUtil.sendJSON(callUrl, requestBody);
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


}
