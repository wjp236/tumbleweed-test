package com.tumbleweed.test.hui10;

import com.tumbleweed.test.base.common.HttpPostUtil;
import com.tumbleweed.test.enn.test.WebCashEcejTest;
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
public class Hui10BaseTest {

    private final static Logger logger = LoggerFactory.getLogger(WebCashEcejTest.class);

    private static final String localServerUrl = "http://localhost:8888/v1";

    /**
     * 测试 base Application  Account
     */
    @Test
    public void base() throws IOException, NoSuchAlgorithmException, InterruptedException {

        Map<String, String> map = new HashMap<>();
        map.put("test1", "test1");
        map.put("test2", "test2");

        String body = new Gson().toJson(map);

        String accountId = "ad77522034f647f7a88bbd6e7beb026a";

        String token = "ad77522034f647f7a88bbd6e7beb026a";

        String url = localServerUrl + "/Account/" + accountId + "/alarm";

        HttpPostUtil.sendJSON(accountId, token, url, body);

    }


}
