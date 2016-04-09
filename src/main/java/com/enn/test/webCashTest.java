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
        String url = "http://localhost:8080/webCash/pay/app/unifiedOrder";
        HttpPostUtil.sendJSON(url, body);
    }

}
