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
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mylover on 8/11/15.
 */
public class IMTest {

    public Logger log = LogManager.getLogger(IMTest.class);

    @Test
    public void pushMessage() throws NoSuchAlgorithmException, IOException {

        JSONObject json = new JSONObject();
        List<String> senders = new ArrayList<String>();
        senders.add("18600200156");

        json.put("appId", "4aea47a551a5017f0151a550f4370001");
        json.put("pushType", "1");
        json.put("sender", "181231");
        json.put("receiver", senders);
        json.put("msgType", "1");
        json.put("msgContent","msgContent");

//        String mainAccount = "4aea47a551a5017f0151a550f4100000";
//        String token = "12b62a38a74844a5ad608bdf61d762c0";
//        String url = "http://210.72.224.55:8881/2013-12-26/Accounts/" + mainAccount + "/IM/PushMsg";

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";
        String url = "http://localhost:8080/2013-12-26/Accounts/" + mainAccount + "/IM/PushMsg";

        log.info(json.toString());

//        String testJson = "{ \"sender\" : \"53feebe00cf2d6351940edc9\" , \"appId\" : \"4aea47a551a5017f0151a550f4370001\" , \"receiver\" : \"[5417f8a8c4aa5f7efdfeef43]\" , \"msgFileName\" : \"\" , \"msgContent\" : \"{\\\'bizKey\\\':\\\'568dddc20cf21c22840b08fd\\\',\\\'msgType\\\':\\\'10\\\',\\\'msg\\\':\\\'爱兜捞：那额磨。\\\',\\\'msgName\\\':\\\'动态评论\\\'}\", \"pushType\" : 1 , \"msgFileUrl\" : \"\" , \"msgType\" : \"1\" , \"msgDomain\" : \"{chatting_id:0c606b5446e9788c5959f61734100d2f, user_nickname:爱兜捞, user_avatar:http://120.24.76.144:8080/images/2015/1/24/54c32a880cf27025395f1101}\"}";
//        String s = HttpPostUtil.sendJSON(mainAccount, token, url, json.toString());
//        String testJson = "{ \"sender\" : \"53feebe00cf2d6351940edc9\" , \"appId\" : \"4028efe33fc65b56013fc660001f0002\" , \"receiver\" : \"[5417f8a8c4aa5f7efdfeef43]\" , \"msgFileName\" : \"\" , \"msgContent\" : \"{bizKey:568dddc20cf21c22840b08fd, msgType:\\\"10\\\", msg:\\\"爱兜捞：那额磨。\\\", msgName:\\\"动态评论\\\"}\" , \"pushType\" : 1 , \"msgFileUrl\" : \"\" , \"msgType\" : \"1\" , \"msgDomain\" : \"{chatting_id:0c606b5446e9788c5959f61734100d2f, user_nickname:爱兜捞, user_avatar:http://120.24.76.144:8080/images/2015/1/24/54c32a880cf27025395f1101}\"}";

        String testJso1 = "{ \"sender\" : \"53feebe00cf2d6351940edc9\" , \"appId\" : \"4028efe33fc65b56013fc660001f0002\" , \"receiver\" : [\"5417f8a8c4aa5f7efdfeef43\",\"testtest\"] , \"msgFileName\" : \"\" , \"msgContent\" : \"{bizKey:568dddc20cf21c22840b08fd, msgType:\\\"10\\\", msg:\\\"爱兜捞：那额磨。\\\", msgName:\\\"动态评论\\\"}\" , \"pushType\" : 1 , \"msgFileUrl\" : \"\" , \"msgType\" : \"1\" , \"msgDomain\" : \"{chatting_id:0c606b5446e9788c5959f61734100d2f, user_nickname:爱兜捞, user_avatar:http://120.24.76.144:8080/images/2015/1/24/54c32a880cf27025395f1101}\"}";
        String s = HttpPostUtil.sendJSON(mainAccount, token, url, testJso1);
        log.info(s);
    }

    @Test
    public void imAddFriend() throws IOException, NoSuchAlgorithmException {
        String body = "{\"userName\":\"158621\",\"friends\":[\"20150314000000110000000000000010#158612\"]}";
        String appId = "20150314000000110000000000000010";
        String appToken = "17E24E5AFDB6D0C1EF32F3533494502B";
        String url = "http://localhost:8080/2013-12-26/Application/" + appId + "/IM/AddFriend";
        HttpPostUtil.sendJSON(appId, appToken, url, body);
    }


    @Test
    public void imAddFriend1() throws IOException, NoSuchAlgorithmException {
        String body = "{\"userName\":\"87830600000113\",\"friends\":[\"4028efe33fc65b56013fc660001f0002#1\",\"4028efe33fc65b56013fc660001f0002#2\",\"4028efe33fc65b56013fc660001f0002#3\",\"4028efe33fc65b56013fc660001f0002#4\",\"4028efe33fc65b56013fc660001f0002#5\",\"4028efe33fc65b56013fc660001f0002#6\"]}";
        String appId = "4028efe33fc65b56013fc660001f0002";
        String appToken = "dfasdsfuwerasdn23dsaf";
        String url = "http://localhost:8080/2013-12-26/Application/" + appId + "/IM/AddFriend";
        HttpPostUtil.sendJSON(appId, appToken, url, body);
    }


    @Test
    public void imDelFriend() throws IOException, NoSuchAlgorithmException {
        String body = "{\"userName\":\"87830600000113\",\"friends\":[\"4028efe33fc65b56013fc660001f0002#1\",\"4028efe33fc65b56013fc660001f0002#2\",\"4028efe33fc65b56013fc660001f0002#3\",\"4028efe33fc65b56013fc660001f0002#4\",\"4028efe33fc65b56013fc660001f0002#5\"]}";
        String appId = "4028efe33fc65b56013fc660001f0002";
        String appToken = "dfasdsfuwerasdn23dsaf";
        String url = "http://localhost:8080/2013-12-26/Application/" + appId + "/IM/DelFriend";
        HttpPostUtil.sendJSON(appId, appToken, url, body);
    }

    @Test
    public void testClient() throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {


        JSONObject json = new JSONObject();
        json.put("groupId", "1234");
        json.put("appId", "测试");

        String body = "{\"pushType\":\"1\",\"appId\":\"8a48b5514fd49643014fef13e6524188\",\"sender\":\"8001086600000002\",\"receiver\":[\"264861553\"],\"msgType\":\"1\",\"msgContent\":\"{\\\"messageId\\\":42,\\\"chatId\\\":8862,\\\"senderType\\\":1,\\\"senderId\\\":8001086600000002,\\\"senderRoleType\\\":null,\\\"senderAvatar\\\":\\\"http://image.lv-guanjia.com/1005/activity/264768690/p/p/14d9e31e061/61929f21cf47ac74bde9f6644a3054c922c.jpg\\\",\\\"senderNickname\\\":\\\"荡客小秘书\\\",\\\"phone\\\":\\\"\\\",\\\"receiverType\\\":1,\\\"receiverId\\\":264861553,\\\"receiverRoleType\\\":null,\\\"chatAvatar\\\":null,\\\"chatTitle\\\":null,\\\"messageType\\\":5,\\\"contentType\\\":1,\\\"content\\\":\\\"{\\\\\\\"isStranger\\\\\\\":null,\\\\\\\"text\\\\\\\":\\\\\\\"欢迎你进入荡客。如果你在使用过程中有任何的问题或建议，记得给我发信反馈哦。\\\\\\\",\\\\\\\"isInclude\\\\\\\":null}\\\",\\\"creator\\\":8001086600000002,\\\"createDate\\\":1448347414567}\",\"msgDomain\":\"dangkr\",\"msgFileName\":null,\"msgFileUrl\":null}";

        String channelAccount = "4028eb25444d379701444d426e640001";

        String url = "http://tumbleweed.wang/platserver/checkContent/check";

        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        String authorization = channelAccount + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/json;charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Authorization", authorization);

        HttpEntity entity = new StringEntity(json.toString(), "UTF-8");
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();

        log.info("状态:" + status + ";返回包体:" + conResult);

    }

    @Test
    public void createGroup() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("name").addText("testkkjkjkjk");
        root.addElement("type").addText("2");
        root.addElement("declared").addText("12");
        root.addElement("permission").addText("1");
        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc660004a0003";
        String token = "72cc0014d5584e42a3db9de3847eb9dd";
        String url = "http://192.168.178.219:8080/2013-12-26/SubAccounts/" + mainAccount + "/Group/CreateGroup";
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String s = HttpPostUtil.sendXML(mainAccount, token, url, body);
        log.info(s);
    }

    @Test
    public void queryGroup() throws NoSuchAlgorithmException, IOException {

        JSONObject json = new JSONObject();
        json.put("groupId", "1234");
        json.put("appId", "4028efe33fc65b56013fc660001f0002");
        String appId = "4028efe33fc65b56013fc660004a0003";
        String token = "72cc0014d5584e42a3db9de3847eb9dd";
        String url = "http://192.168.178.219:8080/2013-12-26/SubAccounts/" + appId + "/Group/QueryGroupDetail";
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String s = HttpPostUtil.sendJSON(appId, token, url, json.toString());
        log.info(s);
    }


    @Test
    public void clientHistroyMsg() throws NoSuchAlgorithmException, IOException {

        JSONObject json = new JSONObject();

        json.put("appId", "4028efe33fc65b56013fc660001f0002");
        json.put("pushType", "1");
        json.put("sender", "181231");
        json.put("msgType", "1");

        String appId = "4028efe33fc65b56013fc660001f0002";
        String token = "";
        String url = "http://192.168.178.219:8080/2013-12-26/AppId/" + appId + "/IM/ClientHistroyMsg";
        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(appId, token, url, json.toString());

        log.info(s);
    }

    @Test
    public void recentlyContactsList() throws NoSuchAlgorithmException, IOException {

        JSONObject json = new JSONObject();
        List<String> senders = new ArrayList<String>();
        senders.add("18600200156");

        json.put("appId", "4028efe33fc65b56013fc660001f0002");
        json.put("pushType", "1");
        json.put("sender", "181231");
        json.put("receiver", senders);
        json.put("msgType", "1");

        String mainAccount = "4028efe33fc65b56013fc660001f0002";
        String token = "";
        String url = "http://192.168.178.219:8080/2013-12-26/AppId/" + mainAccount + "/IM/RecentlyContactsList";
        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(mainAccount, token, url, json.toString());

        log.info(s);
    }



    @Test
    public void aaas() {

        String subAccountSid = "4028eb25444d379701444d426e640001";
        String subAccountSidToken = "ff7e65d6dfec46cfbf3cf21abdd096d7";
        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("appId").addText("297e7c37444db2f201444db340e40000");
        root.addElement("appName").addText("1");
        root.addElement("industry").addText("0");

        String body = document.asXML();

        String url = "http://192.168.178.90:8080/2013-12-26/Accounts/" + subAccountSid + "/ModifyApplication";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        try {
            String sfs = HttpPostUtil.sendXML(subAccountSid, subAccountSidToken, url, body);
            log.info(sfs);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public void returnTT(String mainAccout, String token, String url,
                         String body) throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        String httpurl = url + "?sig=" + sig + "&confid=10007634";

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

        log.info("状态:" + status + ";返回包体:" + conResult);

    }


    /**
     * IM – 离线消息通知<环球教育
     *
     * @throws java.security.NoSuchAlgorithmException
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    @Test
    public void CheckOfflineMsg() throws NoSuchAlgorithmException,
            ClientProtocolException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root =
                document.addElement("Request");
        root.addElement("appId").addText("297e7c37444db2f201444db340e40000");
        String body = document.asXML();

        String channelAccount = "4028eb25444d379701444d426e640001";
        String channelToken = "ff7e65d6dfec46cfbf3cf21abdd096d7";
        String url = "http://192.168.178.90:8080/2013-12-26/Accounts/" + channelAccount + "/IM/CheckOfflineMsg";
        returnTT(channelAccount, channelToken, url, body);
    }


    public void sf() {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://www.yuntongxun.com");
        httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
        httppost.setHeader("Accept", "application/xml");

        HttpResponse httpresponse = null;
        try {
            httpresponse = httpclient.execute(httppost);
            String conResult = EntityUtils.toString(httpresponse.getEntity());
            StatusLine statusLine = httpresponse.getStatusLine();
            int status = statusLine.getStatusCode();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //更新证书 wjp
    @SuppressWarnings("static-access")
    @Test
    public void updateCert() throws NoSuchAlgorithmException, IOException {

        String corpId = "yuntongxun";

        String url = "http://192.168.178.219:8080/2013-12-26/Corp/" + corpId + "/Apns/UpdateCert";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML("", "", url, "");

        log.info("----------wjp--------" + s);

    }

    @Test
    public void getErrorCodejson() throws NoSuchAlgorithmException, IOException {

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        JSONObject j = new JSONObject();
        j.put("errorCode", list);

        String appId = "4028efe33fc65b56013fc660001f0002";
        String token = "dfasdsfuwerasdn23dsaf";
        String url = "http://localhost:8080/2013-12-26/Application/" + appId + "/IM/GetErrorDescribe";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        log.info(j);

        String s = HttpPostUtil.sendJSON(appId, token, url, j.toString());
        log.info(s);
    }

    @Test
    public void getErrorCodexml() throws NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("errorCode").addText("1");
        root.addElement("errorCode").addText("2");
        String body = document.asXML();

        String appId = "4028efe33fc65b56013fc660001f0002";
        String token = "dfasdsfuwerasdn23dsaf";
        String url = "http://localhost:8080/2013-12-26/Application/" + appId + "/IM/GetErrorDescribe";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        log.info(body);

        String s = HttpPostUtil.sendXML(appId, token, url, body);
        log.info(s);
    }

    @Test
    public void authCode() throws NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("userName").addText("18600200156");

        String appId = "4028efe33fc65b56013fc660001f0002";
        String token = "dfasdsfuwerasdn23dsaf";
        String url = "http://localhost:8080/2013-12-26/Application/" + appId + "/IM/AuthCode";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(appId, token, url, document.asXML());
        log.info(s);
    }


    @Test
    public void authCodejson() throws NoSuchAlgorithmException, IOException {

        JSONObject jo = new JSONObject();
        jo.put("userName", "18600200156");

        String appId = "4028efe33fc65b56013fc660001f0002";
        String token = "dfasdsfuwerasdn23dsaf";
        String url = "http://localhost:8080/2013-12-26/Application/" + appId + "/IM/AuthCode";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendJSON(appId, token, url, jo.toString());
        log.info(s);
    }

}
