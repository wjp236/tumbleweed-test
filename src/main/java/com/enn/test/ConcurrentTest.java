package com.enn.test;

import com.alibaba.fastjson.JSON;
import com.enn.util.DateUtil;
import com.enn.util.DateUtils;
import com.enn.util.MD5SignAndValidate;
import com.enn.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 描述:压力测试客户端
 *
 * @author: mylover
 * @Time: 15/12/2016.
 */
public class ConcurrentTest {

    private static int thread_num = 500;
    private static int client_num = 1000;
    private static Map tradeParamMap = new HashMap();

    static {
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "123456");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("payer_no", "88888888");
        tradeParamMap.put("business_code", "A00001OS0011");
        tradeParamMap.put("trade_desc", "订单测试");
        tradeParamMap.put("trade_detail", "订单测试");
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
        tradeParamMap.put("order_amt", "100");
        // 实际交易金额
        tradeParamMap.put("trade_amt", "90");
        // 手续费
        tradeParamMap.put("fee_amt", "0.0");
        // 渠道金额
        tradeParamMap.put("channel_amt", "70");


        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("balance_amt", "15.00");
        otherAmt.put("voucher_amt", "5.00");
        otherAmt.put("voucher_id", "123456789");


        Map<String, String> otherAmtWithSign = MD5SignAndValidate.signData(otherAmt, "ecejpay");
        tradeParamMap.put("other_amt", JSON.toJSONString(otherAmtWithSign));


    }

    public static void main(String[] args) {
        int size = tradeParamMap.size();
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semp = new Semaphore(thread_num);
        for (int index = 0; index < client_num; index++) {
            final int NO = index;
            Runnable run = () -> {
                try {
                    semp.acquire();
                    System.out.println("Thread:" + NO);
                    String host = "http://10.99.23.42:7001/KMQueryCenter/query.do?";

                    tradeParamMap.put("merc_order_no", StringUtil.getUUID4MD5());

                    String body = JSON.toJSONString(tradeParamMap);
                    URL url = new URL(host);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    PrintWriter out = new PrintWriter(connection.getOutputStream());
                    out.print(body);
                    out.flush();
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = "";
                    String result = "";
                    while ((line = in.readLine()) != null) {
                        result += line;
                    }
                    System.out.println("第：" + NO + " 个");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            exec.execute(run);
        }
        exec.shutdown();

    }

}