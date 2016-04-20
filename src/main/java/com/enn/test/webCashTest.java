package com.enn.test;

import com.enn.common.HttpPostUtil;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mylover on 4/9/16.
 */
public class webCashTest {

    /**
     * 统一下单
     */
    @Test
    public void test1() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("id","123456");
        String body = json.toString();
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String url = "http://localhost:8080/webCash/custom/app/setting";
        HttpPostUtil.sendJSON(url, body);
    }


    /**
     * 调起收银台
     */
    @Test
    public void test2() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("id","123456");
        String body = json.toString();
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String url = "http://localhost:8080/webCash/pay/app/unifiedOrder";
        HttpPostUtil.sendJSON(url, body);
    }

    /**
     * 设置成功后端回调
     */
    @Test
    public void test3() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("thr_user_no","90090");
        json.put("result", "SUCCESS");
        json.put("thr_login_no","liuzw");
        String body = json.toString();
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String url = "http://10.37.149.92:8351/3rd/pay/notifyBandResult";
        HttpPostUtil.sendJSON(url, body);
    }

    /**
     * 信息查询
     */
    @Test
    public void test4() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("id","123456");
        String body = json.toString();
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String url = "http://localhost:8080/webCash/custom/app/info";
        HttpPostUtil.sendJSON(url, body);
    }

    /**
     * 支付成功后端回调
     */
    @Test
    public void test5() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("thr_user_no","90090");
        json.put("result", "SUCCESS");
        json.put("thr_login_no","liuzw");
        String body = json.toString();
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String url = "http://10.37.149.92:8351/3rd/pay/notifyBandResult";
        HttpPostUtil.sendJSON(url, body);
    }

}
