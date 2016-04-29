package com.enn.common;

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

/**
 * Created by mylover on 3/29/16.
 */
public class HttpPostUtil {

    public static Logger logger = LogManager.getLogger(HttpPostUtil.class);

    public static String sendJSON(String url, String body)
            throws IOException, NoSuchAlgorithmException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        httppost.setHeader("Content-Type", "application/json;charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        HttpEntity entity = new StringEntity(body, "UTF-8");
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();

        logger.info("状态:" + status + ";返回包体:\n\n" + conResult + "\n\n");

        return conResult;

    }

}
