package com.hui10.test;

import com.base.common.HttpPostUtil;
import com.enn.test.WebCashEcejTest;
import com.enn.util.DateUtil;
import com.enn.util.DateUtils;
import com.enn.util.MD5SignAndValidate;
import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mylover on 4/9/16.
 */
public class TransactionEcejTest {

    private final static Logger log = LoggerFactory.getLogger(WebCashEcejTest.class);

    private static final String localServerUrl = "http://localhost:8080/webCash";
    private static final String devServerUrl = "http://172.16.254.222:9022/webCash";
    private static final String testServerUrl = "http://10.32.32.33:9003/webCash";
    private static final String biztestServerUrl = "http://10.32.15.41:8080/Transaction";
    private static final String prodServerUrl = "http://inpay.local/Transaction";

    /**
     * 下单
     */
    @Test
    public void trade() throws IOException, NoSuchAlgorithmException, InterruptedException {

        HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "123456");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("payer_no", "88888888");
        tradeParamMap.put("business_code", "A00001OS0011");
        tradeParamMap.put("trade_desc", "订单测试");
        tradeParamMap.put("trade_detail", "订单测试");
        tradeParamMap.put("merc_order_no", DateUtils.getCurrentDateTime());
        tradeParamMap.put("currency", "CNY");
        tradeParamMap.put("req_ip", "123.12.12.123");
        tradeParamMap.put("time_expire", DateUtil.beforeNHourToNowDate(-1));
        tradeParamMap.put("city_code", "0800");
        tradeParamMap.put("receive_no", "80126913246453768");
        tradeParamMap.put("trade_channel", "ZFB");
        tradeParamMap.put("trade_mode", "IMMEDIATEPAY");
        tradeParamMap.put("server_notify_url", "http://localhost:8080");
        tradeParamMap.put("pay_type", "1");
        tradeParamMap.put("client_type", "1");
        tradeParamMap.put("consume_type", "31");


        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("balance_amt", "15.00");
        otherAmt.put("voucher_amt", "5.00");
        otherAmt.put("voucher_id", "123456789");


        Map<String, String> otherAmtWithSign = MD5SignAndValidate.signData(otherAmt, "ecejpay");
//        tradeParamMap.put("other_amt", new Gson().toJson(otherAmtWithSign));



        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "ecejpay");

        String body = new Gson().toJson(tradeParamMapWithSign);

        log.info("body:\n" + body);

        String appId = "A99999";

        String token = "123456";

        String url = localServerUrl + "/" + appId + "/pay/unifiedOrder";

//        String url = "http://localhost:8080" + "/" + appId + "/pay/xinyipay/callBack";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

}
