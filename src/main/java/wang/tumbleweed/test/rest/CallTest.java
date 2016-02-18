package wang.tumbleweed.test.rest;

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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.junit.Test;
import wang.tumbleweed.common.Base64;
import wang.tumbleweed.common.HttpPostUtil;
import wang.tumbleweed.common.MD5;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mylover on 8/11/15.
 */
public class CallTest implements Runnable {

    public Logger log = LogManager.getLogger(CallTest.class);

    // 双向回呼 wjp
    @SuppressWarnings("static-access")
    @Test
    public void doubleCallback() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("CallBack");

        root.addElement("from").addText("18600200156");
        root.addElement("to").addText("18210819960");
        root.addElement("isVideo").addText("true");
        root.addElement("callStateUrl").addText("http://192.168.178.219:8080/ivrDialVoice/wjp");

        String body = document.asXML();

        String subAccout = "4028efe33fc65b56013fc660004a0003";
        String token = "72cc0014d5584e42a3db9de3847eb9dd";

        String url = "http://localhost:8080/2013-12-26/SubAccounts/" + subAccout + "/Calls/Callback";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(subAccout, token, url, body);

        log.info(s);
    }

    // 满堂红双向回呼 wjp
    @SuppressWarnings("static-access")
    @Test
    public void doublePartnerCallback() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("CallbackAgent");
        Element business = root.addElement("business");
        business.addElement("userid").addText("110");
        business.addElement("storeid").addText("120");
        business.addElement("houseid").addText("130");
        root.addElement("from").addText("01052823175");
        root.addElement("to").addText("18600200156");
        root.addElement("customerSerNum").addText("18210819960");

        String body = document.asXML();

        String subAccout = "4028efe33fc65b56013fc660004a0003";
        String token = "72cc0014d5584e42a3db9de3847eb9dd";

        String url = "http://192.168.178.219:8080/2013-12-26/SubAccounts/"
                + subAccout + "/partner/CallbackAgent";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(subAccout, token, url, body);

        log.info(s);
    }

    // 语音验证码 wjp
    @SuppressWarnings("static-access")
    @Test
    public void voiceXML() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("VoiceVerify");

        // 必选
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("verifyCode").addText("sbsbsbsb");
        root.addElement("to").addText("18600200156");
        root.addElement("callStateUrl").addText(
                "http://192.168.178.219:8080/2013-12-26/Calls/voiceCapctha");

        // 可选
        root.addElement("playTimes").addText("2");// 播放次数，1－3次
        root.addElement("respUrl").addText(
                "http://192.168.178.219:8088/ivrDialVoice/wjp");// 用户接听呼叫后，发起请求通知应用侧。
        root.addElement("displayNum").addText("125909001920");// 显示号码，显示权由服务器控制
        // 滴滴使用12590900197，极胜使用125909001920
        // 语音验证码的内容全部播放此节点下的全部语音文件，也就是实现了语音验证码功能播放用户自己的语音文件。同时支持上面welcomePrompt的逻辑（语音文件格式为wav）,可支持多个文件，用;分隔
        root.addElement("playVerifyCode").addText("Wel.wav;a.wav");
        root.addElement("lang").addText("en");
        root.addElement("userData").addText("55555");
        // root.addElement("maxCallTime").addText("20");//最大通话时长
        // 值为0 表示将每一次识别结果都推给客户；1　表示收到被叫挂机时才推送识别结果；
        root.addElement("callStatePush").addText("0");

        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        // String url =
        // "http://118.194.243.239:8881/2013-12-26/Accounts/"+mainAccount+"/Calls/VoiceVerify";
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccount + "/Calls/VoiceVerify";
        // String url =
        // "http://192.168.21.56:8881/2013-12-26/Accounts/"+mainAccount+"/Calls/VoiceVerify";
        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(mainAccount, token, url, body);

        log.info("----------wjp--------" + s);

    }

    @SuppressWarnings("static-access")
    @Test
    public void voiceJSON() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        JSONObject json = new JSONObject();
        // 必选
        json.put("appId", "4028efe33fc65b56013fc660001f0002");
        json.put("verifyCode", "222222");
        json.put("to", "186002001562");
        json.put("callStateUrl",
                "http://192.168.178.219:8080/2013-12-26/Calls/voiceCapctha");

        // 可选
        json.put("playTimes", "2");
        json.put("respUrl", "http://192.168.178.219:8080/ivrDialVoice/wjp");
        json.put("displayNum", "01057234444");
        json.put("playVerifyCode", "Wel.wav;a.wav");
        json.put("lang", "en");// zh或en;默认为zh
        json.put("userData", "personal Data");
        json.put("maxCallTime", "1");
        json.put("callStatePush", "0");

        String body = json.toString();

        System.err.println("body=" + body);

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccount + "/Calls/VoiceVerify";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendJSON(mainAccount, token, url, body);
        log.info("----------wjp-----" + s);
    }

    // 营销外呼
    @SuppressWarnings("static-access")
    @Test
    public void LandingCalls() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("LandingCall");

        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("to").addText("18600200156");
        root.addElement("mediaTxt").addText("'304‘");
        root.addElement("replayDtmf").addText("1");
        root.addElement("inputDtmfPrompt").addText("2");
        root.addElement("waitDtmfTime").addText("3");
        root.addElement("playMode").addText("4");
        root.addElement("respUrl").addText("http://localhost:8080/ivrDialVoice/wjp");

        String body = document.asXML();
        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
//        String url = "http://192.168.21.56:8881/2013-12-26/Accounts/" + mainAccount + "/Calls/LandingCalls";
        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccount + "/Calls/LandingCalls";
        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(mainAccount, token, url, body);

        log.info(s);
    }


    // 营销外呼 callState
    @SuppressWarnings("static-access")
    @Test
    public void LandingCallsState() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("LandingCall");

        root.addElement("callSid").addText("16012615042692190001000400004668");
        root.addElement("action").addText("SellingCall");
        root.addElement("number").addText("'18210198380‘");
        root.addElement("state").addText("1");
        root.addElement("duration").addText("2");
        root.addElement("userData").addText("3");

        String body = document.asXML();
        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://localhost:8080/2013-12-26/inner/Calls/LandingCall/CallState";

        returnTT(mainAccount, token, url, body);

    }

    /**
     * ccpuserdata
     *
     * @return
     */
    public String getCcpUserdate() {
        net.sf.json.JSONObject json= new net.sf.json.JSONObject();
        json.put("respUrl", "http://192.168.21.56:8881/ivr/xiaomi/success.do");
        json.put("cbContentType","xml");
        json.put("appId", "ff808081517a924601517af966a10000");
        log.info("ccpuserdata: {}", json.toString());
        String ccpUserData = "";
        try {
            ccpUserData = URLEncoder.encode(json.toString(), "utf-8");
            ccpUserData = URLEncoder.encode(ccpUserData, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return ccpUserData;
    }


    public void returnTT(String mainAccout, String token, String url,
                         String body) throws NoSuchAlgorithmException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        log.info(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        String httpurl = url + "?ccpuserdata=" + getCcpUserdate();

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);
        httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
        httppost.setHeader("Accept", "application/xml");
        httppost.setHeader("Authorization", authorization);

        HttpEntity entity = new StringEntity(body, "UTF-8");
        httppost.setEntity(entity);

        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();

        log.info("状态:" + status + ";\n返回包体:" + conResult);

    }

    @SuppressWarnings("static-access")
    @Test
    public void LandingCallsNew() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("LandingCall");

        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");// 必选
        root.addElement("to").addText("15011308304");// 被叫号码 必选
        root.addElement("mediaTxt").addText("304");// 文本文件名称
        root.addElement("mediaName").addText("1.wav");// 语音文件名称，格式
        // wav。与mediaTxt不能同时为空。当不为空时mediaTxt属性失效。

        root.addElement("mediaNameType").addText("0");// 语音文件名的类型，默认值为0，表示用户语音文件；　值为1表示平台通用文件。
        // 此为json参数，在xml时为属性type值。

        root.addElement("displayNum").addText("125909001920");// 显示号码，显示权由服务器控制
        root.addElement("playTimes").addText("2");// 循环播放次数，1－3次，默认播放1次。
        root.addElement("respUrl").addText(
                "http://192.168.178.67:8080/ivrDialVoice/wjp");// 用户接听呼叫后，发起请求通知应用侧

        root.addElement("userData").addText("userData");// 用户私有数据
        root.addElement("maxCallTime").addText("40");// 最大通话时长 s
        // root.addElement("vid").addText("");//发音人编号。目前系统只有一个人，所以此参数先不对外开放
        root.addElement("speed").addText("0");// 发音速度。取值-500～+500。

        root.addElement("volume").addText("10");// 音量。取值-20 -- +20，缺省0

        root.addElement("pitch").addText("0");// 音调。取值-500 -- +500 ，缺省0
        root.addElement("bgsound").addText("1");// 背景音编号。取值1、2
        String body = document.asXML();

		/*
         * String subAccout = "4028efe33fc65b56013fc660004a0003"; String token =
		 * "72cc0014d5584e42a3db9de3847eb9dd";
		 *
		 * String url =
		 * "http://192.168.178.67:8080/2013-12-26/SubAccounts/"+subAccout
		 * +"/Calls/Callback";
		 */

		/*
		 * String mainAccount="4028eb25444d379701444d426e640001"; String
		 * token="ff7e65d6dfec46cfbf3cf21abdd096d7";//红建
		 */
        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043"; // 我的
        String url = "http://192.168.178.219:8080/2013-12-26/Accounts/"
                + mainAccount + "/Calls/LandingCalls";
        // String url =
        // "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/Calls/PrepareDial";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(mainAccount, token, url, body);

        log.info(s);
    }

    /**
     * 模拟语音验证码客户端进行总联调！
     *
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
	/*
	 * @Test public void totalVoiceTest() throws ClientProtocolException,
	 * NoSuchAlgorithmException, IOException{ Document document =
	 * DocumentHelper.createDocument(); Element root
	 * =document.addElement("Request");
	 *
	 * root.addElement("accountId").addText("4028efe33fc65b56013fc65be7cc0000");
	 * root.addElement("sign").addText("15060411474558510001000200000266");
	 * root.addElement("appId").addText("15011308304");
	 * root.addElement("callId").addText("12");//应答时间yyyymmddhhmmss
	 *
	 * root.addElement("capcthaCode").addText("0");//外呼状态（ 0 正常通话 1 被叫未应答 2
	 * 外呼失败）
	 * root.addElement("to").addText("1501130830415011308304");//结束时间yyyymmddhhmmss
	 * root
	 * .addElement("displayNum").addText("15011308304");//开始呼叫时间yyyymmddhhmmss
	 * root.addElement("playTimes").addText("userData");//用户私有数据。和语音验证码接口的私有数据一致
	 *
	 * root.addElement("respUrl").addText(
	 * "http://118.194.243.239:8881/2013-12-26/Calls/voiceCapctha");//回调地址
	 * root.addElement("userData").addText("userData");//用户私有数据。和语音验证码接口的私有数据一致
	 * String body = document.asXML();
	 *
	 * String mainAccount="4028efe33fc65b56013fc65be7cc0000"; String
	 * token="5091250ed5154c31ab286664eed13043";
	 *
	 * String url =
	 * "http://192.168.178.219:8080/2013-12-26/Accounts/"+mainAccount
	 * +"/Calls/VoiceVerify";
	 *
	 * HttpPostUtil HttpPostUtil = new HttpPostUtil();
	 *
	 * String s=HttpPostUtil.send(mainAccount, token, url, body);
	 * System.err.println("----url---"+url); System.err.println("----wjp---"+s);
	 * }
	 */

    /**
     * 验证码被叫摘机
     *
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void voiceVerifyDone() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");

        root.addElement("action").addText("voiceCapctha");
        root.addElement("callId").addText("15061216590396940001000200000001");
        root.addElement("number").addText("18600200156");
        root.addElement("answertime").addText("12");// 应答时间yyyymmddhhmmss
        root.addElement("status").addText("1");// 外呼状态（ 0 正常通话 1 被叫未应答 2 外呼失败）
        root.addElement("endtime").addText("1501130830415011308304");// 结束时间yyyymmddhhmmss
        root.addElement("startcalltime").addText("yyyymmddhhmmss");// 开始呼叫时间yyyymmddhhmmss
        root.addElement("userData").addText("userData");// 用户私有数据。和语音验证码接口的私有数据一致
        root.addElement("hisuncallId").addText("11111");// 用户私有数据。和语音验证码接口的私有数据一致
        root.addElement("calltime").addText("1");
        root.addElement("alertingtime").addText("1");
        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Calls/voiceCapctha";// 本地

        // String url =
        // "http://118.194.243.239:8881/2013-12-26/Calls/voiceCapctha";//外网

        // String url =
        // "http://192.168.21.56:8881/2013-12-26/Calls/voiceCapctha";//开发环境
        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(mainAccount, token, url, body);
        System.err.println("----url---" + url);
        System.err.println("----wjp---" + s);
    }

    /**
     * 验证码被叫摘机
     *
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     */
    @SuppressWarnings("static-access")
    @Test
    public void voiceVerifyDoneJson() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        JSONObject json = new JSONObject();
        // 必选
        json.put("action", "voiceCapctha");
        json.put("callId", "15061216590396940001000200000001");
        json.put("number", "186002001562");

        // 可选
        json.put("status", "2");
        json.put("endtime", "http://192.168.178.219:8080/ivrDialVoice/wjp");
        json.put("startcalltime", "01057234444");
        json.put("userData", "Wel.wav;a.wav");
        json.put("hisuncallId", "Wel.wav;a.wav");
        String body = json.toString();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://localhost:8080/2013-12-26/Calls/voiceCapctha";// 本地

        // String url =
        // "http://118.194.243.239:8881/2013-12-26/Calls/voiceCapctha";//外网

        // String url =
        // "http://192.168.21.56:8881/2013-12-26/Calls/voiceCapctha";//开发环境
        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendJSON(mainAccount, token, url, body);
        System.err.println("----url---" + url);
        System.err.println("----wjp---" + s);
    }


    public void run() {

    }

    @Test
    public void testRun() throws NoSuchAlgorithmException, IOException {

        for (int i = 0; i < 10000; i++) {
            CallTest test = new CallTest();
            test.run();
        }

    }


}
