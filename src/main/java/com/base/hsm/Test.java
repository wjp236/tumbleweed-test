package com.base.hsm;

import com.base.common.Base64;
import com.base.common.MD5;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public Logger log = LogManager.getLogger(Test.class);

    @org.junit.Test
    public void test() throws NoSuchAlgorithmException, IOException {

        String s = "000741303030303058";
        String result = String.valueOf(Integer.parseInt(s,16));
        System.out.println(result);

    }
}
