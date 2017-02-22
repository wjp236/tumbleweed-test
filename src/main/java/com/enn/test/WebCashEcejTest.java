package com.enn.test;

import com.base.common.EncryptUtil;
import com.enn.common.HttpPostUtil;
import com.enn.model.XinyipayPo;
import com.enn.util.DateUtils;
import com.enn.util.ShardTimeUtil;
import com.google.gson.Gson;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by mylover on 4/9/16.
 */
public class WebCashEcejTest {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(WebCashEcejTest.class);

    private static final String localServerUrl = "http://localhost:8080/webCash";
    private static final String serverUrl = "http://222.222.120.75:8081/webCash-core/webCash";
    private static final String devServerUrl = "http://devpay.ecej.com/webCash-core/webCash";
    private static final String serverDevUrl = "http://10.37.148.254:9022/webCash-core/webCash";
    private static final String serverDevUrl10 = "http://10.37.149.26:8730/webCash";
    private static final String testServerUrl = "http://183.196.130.125:9022/webCash-core/webCash";
    private static final String testServerUrlYun = "http://sipay.ecej.com:9022/webCash-core/webCash";
    private static final String biztestServerUrl = "http://stpay.ecej.com:8081/webCash-core/webCash";
    private static final String proServerUrl = "http://pay.ecej.com/webCash-core/webCash";

    @Test
    public void makePwd() {
        String pwd = EncryptUtil.md5(EncryptUtil.md5("123456") + "123456");
        log.info(pwd);
    }

    /**
     * signature签名
     */
    @Test
    public void binding() throws IOException, NoSuchAlgorithmException, InterruptedException {
//        Document document = DocumentHelper.creheihateDocument();
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


        String url = testServerUrlYun + "/xinyi/make/signature";

        log.info(url);
        for (int i = 0;
             i < 1
                ; i++) {
            HttpPostUtil.sendJSON(url, body);
            Thread.sleep(10);
        }
    }

    /**
     * 统一下单
     */
    @Test
    public void unifiedOrder() throws IOException, NoSuchAlgorithmException, InterruptedException {
        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "000000");
        json.put("req_ip", "127.0.0.1");
        json.put("biz_type", "GOODS");
        json.put("trade_amt", "0.0");
        json.put("trade_desc", "篮球");
        json.put("req_time",    "20161117000000");
        json.put("time_expire", "20170222234500");
        json.put("trade_mode", "GUARANTEEPAY");
        json.put("trade_detail", "秘制篮球");
        json.put("currency", "CNY");
        json.put("pay_type", "APP");
        json.put("merc_order_no", "20160624062822");
        json.put("attach", "userdata");
        json.put("receive_no", "80126913246453768");//10:801108198899916808 //9:80126913246453768 测试:80126914620088320
        json.put("notify_url", "http://10.37.148.254:9022/webCash-core/webCash/xinyi/make/metCallBack");
        json.put("pay_type", "APP");
        json.put("cashAmt", "0.0");
        json.put("priAmt", "2.0");
        json.put("packAmt", "1.0");
        json.put("feeAmt", "0.02");

        String body = json.toString();

        String urlsignature = testServerUrlYun + "/xinyi/make/signature";

        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        log.info(requestBody);

        String url = testServerUrlYun + "/pay/unifiedOrder";

//        String urlProtobuf = biztestServerUrl + "/pay/unifiedOrder/protobuf";

        log.info(url);

        HttpPostUtil.sendJSON(url, requestBody);

    }

    @Test
    public void shardTest() throws Exception {
        log.info(ShardTimeUtil.formatShardingTime(ShardTimeUtil.fromTradeNo("124201702221419540000012202624")));
    }

    /**
     * 唤起收银台签名
     */
    @Test
    public void getThdSdk() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("salt","123456");
        json.put("merc_id", "8011056811254598983686");
        json.put("trade_no", "124201702221445590000013251200");
        json.put("req_time","20160428105935");
        json.put("appid", "A000001");
        json.put("tradeChannel", "ZFB");
        log.info(json.toString());

        String body = json.toString();
        String url = testServerUrlYun + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(url, body);

        String callUrl = testServerUrlYun + "/pay/getThdSdk";
        HttpPostUtil.sendJSON(callUrl, requestBody);
    }

    @Test
    public void account() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("salt","123456");
        json.put("merc_id", "8011056811254598983686");
        json.put("trade_no", "124201702221523090000011154048");
        json.put("req_time","20160428105935");
        String body = json.toString();
        log.info(body);
        String urlsignature = testServerUrlYun + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = testServerUrlYun + "/pay/accounting";
        HttpPostUtil.sendJSON(url, requestBody);
    }

    @Test
    public void queryTrade() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("salt","123456");
        json.put("merc_id", "8011056811254598983686");
        json.put("trade_no", "124201702221523090000011154048");
        json.put("req_time","20160428105935");
        String body = json.toString();
        log.info(body);
        String urlsignature = testServerUrlYun + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = testServerUrlYun + "/pay/queryOrderInfo";
        HttpPostUtil.sendJSON(url, requestBody);
    }

    @Test
    public void refund() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt","123456");
        json.put("req_time", DateUtils.getCurrentDateTime());
        json.put("trade_no", "124201702181253400000010039936");
        json.put("merc_refund_no", "WECHAT4518600000000222337");
        json.put("refund_amount", "3.00");
        json.put("currency", "CNY");
        json.put("receiver_no", "80159357405224962");
//        json.put("notify_url", serverUrl + "/xinyi/make/signature");

        String body = json.toString();
        log.info(body);

        String urlsignature = testServerUrlYun + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = testServerUrlYun + "/pay/refund";
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
        json.put("accountDate", "20161111");
        json.put("currency","1");
        json.put("tradeAmount", "1.00");
        json.put("privilegePrice", "1.00");
        json.put("xinyiShell", "1.00");
        json.put("cash", "1.00");
        json.put("payChannel", "1");
        json.put("type", "2");
        json.put("bizType", "PAYMENT");
        log.info(json.toString());

        Gson gson = new Gson();

        XinyipayPo xinyipayPo = gson.fromJson(json.toString(), XinyipayPo.class);

        log.info(xinyipayPo.getAppId());

//        HttpPostUtil.sendJSON(url, json.toString());

    }

    /**
     * 测试disconf
     */
    @Test
    public void disconf() throws IOException, NoSuchAlgorithmException {

        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_time", "20160426163043");
        json.put("signature", "xinyiPay");
        json.put("date", new Date().toString());

        String body = json.toString();

        String url = localServerUrl + "/xinyi/test/disconf";

        log.info(url);

        HttpPostUtil.sendJSON(url, body);
    }

    /**
     * logback
     */
    @Test
    public void logback() throws IOException, NoSuchAlgorithmException {

        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_time", "20160426163043");
        json.put("signature", "xinyiPay");
        json.put("date", new Date().toString());

        String body = json.toString();

        String url = localServerUrl + "/xinyi/test/logback";

        log.info(url);

        HttpPostUtil.sendJSON(url, body);

    }

    /**
     * log4j2
     */
    @Test
    public void log4j2() throws IOException, NoSuchAlgorithmException {

        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_time", "20160426163043");
        json.put("signature", "xinyiPay");
        json.put("date", new Date().toString());

        String body = json.toString();

        String url = localServerUrl + "/xinyi/test/log4j2";

        log.info(url);

        HttpPostUtil.sendJSON(url, body);

    }





}
