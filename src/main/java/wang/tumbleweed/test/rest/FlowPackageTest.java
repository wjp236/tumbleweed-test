package wang.tumbleweed.test.rest;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wang.tumbleweed.common.HttpPostUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FlowPackageTest {

    @Test
    public void dataPlanPackage() throws ClientProtocolException, NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("phoneNum").addText("18310536875");

        JSONObject json = new JSONObject();
        json.put("phoneNum", "18310536874");

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.67:8080/2013-12-26/Accounts/" + mainAccount + "/flowPackage/flowPackage";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();


//		String body = document.asXML();
//		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);

        String body = String.valueOf(json);
        String s = HttpPostUtil.sendJSON(mainAccount, token, url, body);

        System.err.println("--------zzx----------" + s);

    }

    @Test
    public void dataPlan() throws ClientProtocolException, NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("phoneNum").addText("18310536875");
        root.addElement("sn").addText("100004");
        root.addElement("packet").addText("150");
        root.addElement("reason").addText("qqqqq");
        root.addElement("customId").addText("695136f5028d11e5a1610050568e55bd");
        root.addElement("callbackUrl").addText("http://192.168.178.67:8080/ivrDialVoice/zzx");


        JSONObject json = new JSONObject();
        json.put("appId", "4028efe33fc65b56013fc660001f0002");
        json.put("phoneNum", "18310536874");
        json.put("sn", "100004");
        json.put("packet", "150");
        json.put("reason", "qqqqq");
        json.put("customId", "695136f5028d11e5a1610050568e55bd");
        json.put("callbackUrl", "http://192.168.178.67:8080/ivrDialVoice/zzx");


        System.err.println();
        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.67:8080/2013-12-26/Accounts/" + mainAccount + "/flowPackage/flowRecharge";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String body = String.valueOf(json);
        String s = HttpPostUtil.sendJSON(mainAccount, token, url, body);
//		String body = document.asXML();
//		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
        System.err.println("--------zzx----------" + s);

    }

    @Test
    public void dataPlanState() throws ClientProtocolException, NoSuchAlgorithmException, IOException {
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement("Request");
        root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
        root.addElement("rechargeId").addText("09c62d8f028b11e5a1610050568e55bd");
        root.addElement("customId").addText("695136f5028d11e5a1610050568e55bd");


        JSONObject json = new JSONObject();
        json.put("appId", "4028efe33fc65b56013fc660001f0002");
        json.put("rechargeId", "09c62d8f028b11e5a1610050568e55bd");
        json.put("customId", "695136f5028d11e5a1610050568e55bd");

        System.err.println();
        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.67:8080/2013-12-26/Accounts/" + mainAccount + "/flowPackage/flowRechargeStatus";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String body = String.valueOf(json);
        String s = HttpPostUtil.sendJSON(mainAccount, token, url, body);
//		String body = document.asXML();
//		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
        System.err.println("--------zzx----------" + s);

    }

    @RequestMapping(method = RequestMethod.POST, value = "zzx")
    void requrie(HttpServletRequest request) {
        System.err.println("----zzx---" + request);
    }
}
