package com.yuntongxun.test.rest;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import com.base.common.HttpPostUtil;
import com.base.common.MD5;
import com.base.common.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AccountTest {

    public Logger log = LogManager.getLogger(AccountTest.class);

    /*
     *创建子账号
     */
    @Test
    public void SubAccounts() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Map<String, String> map = new HashMap<String, String>();

        map.put("appId", "aaf98f89429833490142983c5c480001");

        map.put("friendlyName", "rrueedde21");

        JSONObject json = JSONObject.fromObject(map);

        String mainAccout = "aaf98fda425ae340014268f01ff9004b";

        String token = "52677a185b8a40aaacc4b597474f4aa9";

        String url = "http://115.29.195.1:6881/2013-12-26/Accounts/"
                + mainAccout + "/SubAccounts";

        returnTT(mainAccout, token, url, json.toString());

    }


    // 修改子账号
    @SuppressWarnings("static-access")
    @Test
    public void modifySubAccounts() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("SubAccount");
        root.addElement("subAccountSid").addText("4028efe33fc65b56013fc660004a0003");
        root.addElement("friendlyName").addText("IOS-VoIP能力DEMO1");

        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/" + accountSid + "/ModifySubAccount";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        log.info(s);
    }

    // ivr外呼接口
    public void returnTT(String mainAccout, String token, String url,
                         String body) throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        String httpurl = url + "?sig=" + sig;

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

    }


    /**
     * 删除语音文件
     *
     * @throws ClientProtocolException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.IOException
     */
    @Test
    public void DelMediaFile() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {


        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appId").addText("297e7c37444db2f201444db340e40000");
        root.addElement("fileName").addText("wait.wav");
        String
                body = document.asXML();


        String channelAccount = "4028eb25444d379701444d426e640001";
        String channelToken = "ff7e65d6dfec46cfbf3cf21abdd096d7";
        String url = "http://192.168.178.90:8080/2013-12-26/Accounts/"
                + channelAccount + "/esb/DelMediaFile";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(channelAccount, channelToken, url, body);

        log.info(s);
    }

    /**
     * 语音文件上传
     *
     * @throws java.io.IOException
     * @throws ClientProtocolException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void MediaFileUpload() throws NoSuchAlgorithmException, ClientProtocolException, IOException {

        String mainAccout = "aaf98fda425ae340014268f01ff9004b";
        String token = "52677a185b8a40aaacc4b597474f4aa9";

        String url = "http://115.29.195.1:6881/2013-12-26/Accounts/"
                + mainAccout + "/Calls/MediaFileUpload?appid=aaf98f89429833490142983c5c480001&filename=wait.wav";

        returnMediaFile(mainAccout, token, url, "");

    }


    // ivr外呼接口
    public void returnMediaFile(String mainAccout, String token, String url,
                                String body) throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        String httpurl = url + "&sig=" + sig;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);
        httppost.setHeader("Content-Type", "application/octet-stream");
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Authorization", authorization);

        File file = new File("F:\\wait.wav");

        InputStream input = new FileInputStream(file);

        byte[] byt = new byte[input.available()];

        input.read(byt);

        ByteArrayEntity byteArray = new ByteArrayEntity(byt);

        httppost.setEntity(byteArray);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();
    }


    @Test
    public void testTempTokenJson() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        String appId = "4028efe33fc65b56013fc660001f0002";
        String url = "http://192.168.178.219:8080/2013-12-26/AppId/" + appId + "/IM50/TempToken?cb=123456&userName=801232121&deviceNo=imei&deviceType=21&sdkVersion=1.0.0&network=1&ttl=600";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();
        log.info("状态:" + status + ";返回包体:" + conResult);

    }

    public boolean isNumber(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]*");
        java.util.regex.Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("static-access")
    @Test
    public void testSwitchs() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        String mainAccout = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.219:8080/2013-12-26/Switchs/"
                + mainAccout;
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String s = HttpPostUtil.sendXML(mainAccout, token, url, "");
        log.info(s);
    }

    /**
     * 获取话单接口
     *
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void getCdr() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");

        root.addElement("orderId").addText("CM1000420160105161637100000");
//        CM1000420150605173635100001
//        CM1000420160105161637100000
        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.21.56:8881/2013-12-26/Accounts/"+mainAccount+"/GetBill";// 本地
//        String url = "http://localhost:8080/2013-12-26/Accounts/"+mainAccount+"/GetBill";// 本地

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        HttpPostUtil.sendXML(mainAccount, token, url, body);
    }

    /**
     * 获取余额
     *
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void getBalance() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        String body = document.asXML();

        String mainAccount = "146182417ac511e49b220050568e55bd";
        String token = "146182507ac511e49b220050568e55bd";

        String url = "http://localhost:8080/2013-12-26/Channel/"+mainAccount+"/partner/QueryChannelBalance";// 本地

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        HttpPostUtil.sendXML(mainAccount, token, url, body);
    }

    /**
     * 获取账号余额
     *
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void getAccountBalance() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("accountSid").addText("4028efe33fc65b56013fc65be7cc0000");
        String body = document.asXML();

        String mainAccount = "146182417ac511e49b220050568e55bd";
        String token = "146182507ac511e49b220050568e55bd";

        String url = "http://localhost:8080/2013-12-26/Channel/"+mainAccount+"/QueryAccountBalance";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        HttpPostUtil.sendXML(mainAccount, token, url, body);
    }

}
