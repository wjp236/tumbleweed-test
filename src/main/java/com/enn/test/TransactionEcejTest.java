package com.enn.test;

import com.base.common.HttpPostUtil;
import com.enn.util.DateUtil;
import com.enn.util.DateUtils;
import com.enn.util.MD5SignAndValidate;
import com.enn.util.SMSUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mylover on 4/9/16.
 */
public class TransactionEcejTest {

    public Logger log = LogManager.getLogger(TransactionEcejTest.class);

    private static final String localServerUrl = "http://localhost:8080/Transaction";
    private static final String devServerUrl = "http://10.37.148.254:9003/Transaction";

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
        // 订单金额
        tradeParamMap.put("order_amt", "10");
        // 实际交易金额
        tradeParamMap.put("trade_amt", "10");
        // 手续费
        tradeParamMap.put("fee_amt", "");
        // 渠道金额
        tradeParamMap.put("channel_amt", "30");

        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "ecejpay");

        String body = new Gson().toJson(tradeParamMapWithSign);

        String appId = "A99999";

        String token = "123456";

        String url = devServerUrl + "/" + appId + "/pay/trade";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

    @Test
    public void sendSms() {
        SMSUtils.sendSms("测试", "18600200156");
    }

}
