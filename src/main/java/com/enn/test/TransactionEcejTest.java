package com.enn.test;

import com.base.common.HttpPostUtil;
import com.enn.util.SMSUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mylover on 4/9/16.
 */
public class TransactionEcejTest {

    public Logger log = LogManager.getLogger(TransactionEcejTest.class);

    private static final String localServerUrl = "http://localhost:8080";
    private static final String devServerUrl = "http://10.37.148.254:9003/transaction-web/Transaction";

    /**
     * 下单
     */
    @Test
    public void trade() throws IOException, NoSuchAlgorithmException, InterruptedException {
        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "000000");
        json.put("req_ip", "127.0.0.1");
        json.put("biz_type", "GOODS");
        json.put("trade_amt", "1.0");
        json.put("trade_desc", "篮球");
        json.put("req_time",    "20161109000000");
        json.put("time_expire", "20161109234500");
        json.put("trade_mode", "GUARANTEEPAY");
        json.put("trade_detail", "秘制篮球");
        json.put("currency", "CNY");
        json.put("pay_type", "APP");
        json.put("merc_order_no", "20160624062822");
        json.put("attach", "userdata");
        json.put("receive_no", "80126914620088320");//10:801108198899916808 //9:80126913246453768 测试:80126914620088320
        json.put("notify_url", "http://10.37.148.254:9022/webCash-core/webCash/xinyi/make/metCallBack");
        json.put("pay_type", "APP");
        json.put("cashAmt", "3.0");
        json.put("priAmt", "2.0");
        json.put("packAmt", "1.0");
        json.put("feeAmt", "0.02");

        String body = json.toString();

        String appId = "inner";

        String token = "123456";

//        String url = devServerUrl + "/" + appId + "/pay/trade";
        String url = localServerUrl + "/" + appId + "/pay/callBack";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, "test");

    }

    @Test
    public void sendSms() {
        SMSUtils.sendSms("测试", "18600200156");
    }

}
