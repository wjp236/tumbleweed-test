package com.base.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
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

/**
 * @author baohj
 * @ClassName: HttpPostCommon
 * @Description: TODO(接口调用)
 * @date 2014年11月28日 下午1:30:09
 */
public class HttpPostUtil {
    public static Logger logger = LogManager.getLogger(HttpPostUtil.class);

    /**
     * @param @param  url
     * @param @return
     * @param @throws ClientProtocolException
     * @param @throws IOException
     * @return String 返回类型
     * @throws java.security.NoSuchAlgorithmException
     * @throws
     * @Title: send
     * @Description: TODO(使用此方法访问接口)
     */
    public static String sendXML(String mainAccout, String token, String url,
                                 String body)
            throws IOException, NoSuchAlgorithmException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        System.out.println("------------authorization=" + authorization);
        String httpurl = url + "?sig=" + sig;
        System.out.println("-------------sig=" + sig);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);
        httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
        httppost.setHeader("Accept", "application/xml");
        httppost.setHeader("Authorization", authorization);
        HttpEntity entity = new StringEntity(body, "UTF-8");
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();

        logger.info("状态:" + status + ";返回包体:" + conResult);

        return "status=" + status + ";conResult=" + conResult;

    }

    public static String sendJSON(String mainAccout, String token, String url,
                                  String body)
            throws ClientProtocolException, IOException, NoSuchAlgorithmException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        System.out.println("------------authorization=" + authorization);
        String httpurl = url + "?sig=" + sig;
        System.out.println("-------------sig=" + sig);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);

        httppost.setHeader("Content-Type", "application/json;charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Authorization", authorization);
        HttpEntity entity = new StringEntity(body, "UTF-8");
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();

        logger.info("状态:" + status + ";返回包体:" + conResult);

        return "status=" + status + ";conResult=" + conResult;

    }
}
