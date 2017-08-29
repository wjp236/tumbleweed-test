package com.tumbleweed.test.yuntongxun.test.rest;

import net.sf.json.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import com.tumbleweed.test.base.common.HttpPostUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ChannelTest {

    public Logger logger = LogManager.getLogger(ChannelTest.class);


    //创建渠道套餐
    @Test
    public void CreateChannelRate() throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("name").addText("testTwo");
        root.addElement("ratedesc").addText("testTwo");
        String body = document.asXML();

        String channelAccount = "402832014c0bf201014c0bffd4090000";
        String channelToken = "77963b7a931377ad4ab5ad6a9cd718aa";
        String url = "http://192.168.178.90:8080/2013-12-26/Channel/" + channelAccount + "/partner/CreateChannelRate";
        HttpPostUtil post = new HttpPostUtil();


        String result = post.sendXML(channelAccount, channelToken, url, body);

    }

    /**
     * 查询渠道套餐
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    @Test
    public void QueryChannelRate() throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {
        /*
         * Document document = DocumentHelper.createDocument(); Element root =
		 * document.addElement("Request");
		 * root.addElement("Appid").addText("ff80808149cc0ef80149cc6cce700000");
		 * root.addElement("DismissConf").addAttribute("confid", "10007634");
		 * String body = document.asXML();
		 */
        String channelAccount = "402832014c0bf201014c0bffd4090000";
        String channelToken = "77963b7a931377ad4ab5ad6a9cd718aa";
        String url = "http://192.168.178.90:8080/2013-12-26/Channel/"
                + channelAccount + "/partner/QueryChannelRate";

        HttpPostUtil post = new HttpPostUtil();


        String result = post.sendXML(channelAccount, channelToken, url, "");
    }

    /**
     * 主账号绑定套餐
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    @Test
    public void BoundAccountRate() throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("accountSid").addText("4028eb25444d379701444d426e640001");
        root.addElement("rateId").addText("212");
        String body = document.asXML();

        String channelAccount = "402832014c0bf201014c0bffd4090000";
        String channelToken = "77963b7a931377ad4ab5ad6a9cd718aa";
        String url = "http://192.168.178.90:8080/2013-12-26/Channel/"
                + channelAccount + "/partner/BoundAccountRate";

        HttpPostUtil post = new HttpPostUtil();


        String result = post.sendXML(channelAccount, channelToken, url, body);
        System.out.println(result);
    }

    /**
     * 关闭主账号
     *
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @Test
    public void closeAccount() throws ClientProtocolException, NoSuchAlgorithmException, IOException {


        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("accountSid").addText("4028eb25444d379701444d426e640001");
        String body = document.asXML();

        String channelAccount = "402832014c0bf201014c0bffd4090000";
        String channelToken = "77963b7a931377ad4ab5ad6a9cd718aa";
        String url = "http://192.168.178.90:8080/2013-12-26/Channel/" + channelAccount + "/partner/closeAccount";


        HttpPostUtil post = new HttpPostUtil();


        String result = post.sendXML(channelAccount, channelToken, url, body);
        System.out.println(result);
    }

    @Test
    public void mans() throws ClientProtocolException, NoSuchAlgorithmException, IOException {


        String channelAccount = "aaf98fda45fde62a0145fe3459d70035";
        String channelToken = "b1ee23060ab74ab99aa481e03002412a";
        String url = "http://42.121.15.84:6881/2013-12-26/Accounts/" + channelAccount + "/Calls/VoiceVerify";

        Map<String, String> map = new HashMap<String, String>();

        map.put("appId", "8a48b5514835561a01483e9149d803d8");

        map.put("verifyCode", "33333");

        map.put("to", "18310536874");


        JSONObject josn = JSONObject.fromObject(map);

        HttpPostUtil post = new HttpPostUtil();
        String body1 = "{\"respUrl\": \"http://sms.ele.me/provider_callback/audio/report/cloopen_audio/\", \"playTimes\": \"2\", \"verifyCode\": \"791134\", \"to\": \"15120024616\", \"appId\": \"8a48b5514835561a01483e9149d803d8\", \"displayNum\": \"4000557117\"}";


        String result = post.sendXML(channelAccount, channelToken, url, body1);
        System.out.println(result);
    }


    /**
     * 2.26.设置渠道主账户虚拟费用
     *
     * @throws java.io.IOException
     * @throws ClientProtocolException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void SetAccountBalance() throws NoSuchAlgorithmException, ClientProtocolException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Account");
        root.addElement("accountSid").addText("4028eb25444d379701444d426e640001");
        root.addElement("balance").addText("10");
        root.addElement("batchNo").addText("111");
        String body = document.asXML();

        String channelAccount = "402832014c0bf201014c0bffd4090000";
        String channelToken = "77963b7a931377ad4ab5ad6a9cd718aa";
        String url = "http://192.168.178.90:8080/2013-12-26/Channel/" + channelAccount + "/SetAccountBalance";

        HttpPostUtil post = new HttpPostUtil();

        String result = post.sendXML(channelAccount, channelToken, url, body);

        System.out.println("result:" + result);
    }


}
