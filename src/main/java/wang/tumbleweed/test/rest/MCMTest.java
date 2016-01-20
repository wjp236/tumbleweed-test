package wang.tumbleweed.test.rest;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import wang.tumbleweed.common.HttpPostUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class MCMTest {

	@Test
	public void sendMCM() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("OSSendMessage");
		root.addElement("appId").addText("4028efe33fc65b56013fc660001f0002");
		root.addElement("msgSid").addText("07221158170000011111111100055004");
		root.addElement("userAccount").addCDATA("aaf98f89429833490142983c5c480001");
		root.addElement("osUnityAccount").addCDATA("<span>aaf98f89429833490142983c5c480001<span>");
		root.addElement("type").addCDATA("1");
		root.addElement("content").addCDATA("111111111111");
		
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/sendmcm";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
	
	@Test
	public void OSSetServiceMode() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("CMC");
		root.addElement("sid").addText("15061216590396940001000200000001");
		root.addElement("mode").addText("0");
		
		
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/OSSetServiceMode";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
	
	@Test
	public void OSTransferAgent() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("CMC");
		root.addElement("sid").addText("15061216590396940001000200000001");
		root.addElement("transferagentid").addText("0");
		
		
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/OSTransferAgent";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
	
	@Test
	public void OSEnterCallService() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("CMC");
		root.addElement("sid").addText("15061216590396940001000200000001");
		
		
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/OSEnterCallService";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
	
	@Test
	public void OSFinishService() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("CMC");
		root.addElement("sid").addText("15061216590396940001000200000001");
		
		
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/OSFinishService";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
	
	@Test//zzx!!!!
	 public void OSSetRebotMode() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("CMC");
		root.addElement("modename").addText("15061216590396940001000200000001");
		
		
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/OSSetRebotMode";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
	
	@Test
	 public void OSTraceService() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("CMC");
		root.addElement("sid").addText("15061216590396940001000200000001");//二选一
		root.addElement("agentid").addText("15061216590396940001000200000001");//二选一参数什么规则
		root.addElement("monitoragentid").addText("111");//必选
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/OSTraceService";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
	
	@Test
	 public void OSEnforceTransfer() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("CMC");
		root.addElement("sid").addText("15061216590396940001000200000001");
		root.addElement("agentid").addText("15061216590396940001000200000001");
		
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/OSEnforceTransfer";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
	
	@Test
	 public void OSSendIRMsg() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		Document document = DocumentHelper.createDocument();
		Element root =document.addElement("OSSendIRMsg");
		root.addElement("msgSid").addText("15061216590396940001000200000001");
	//	root.addElement("msgSid").addText("07221158170000011111111100055004");
		root.addElement("userAccount").addText("15061216590396940001000200000001");
		root.addElement("osUnityAccount").addText("15061216590396940001000200000001");
		root.addElement("description").addText("15061216590396940001000200000001");
		root.addElement("item").addText("15061216590396940001000200000001");
		root.addElement("Itemkey").addText("15061216590396940001000200000001");
		
		
		String mainAccount="4028efe33fc65b56013fc65be7cc0000";
		String token="5091250ed5154c31ab286664eed13043";
		String url = "http://192.168.178.67:8080/2013-12-26/Accounts/"+mainAccount+"/mcm/OSSendIRMsg";
		
		String body = document.asXML();
		String s=HttpPostUtil.sendXML(mainAccount, token, url, body);
		
		System.out.println("----------zzx--------------"+s);
	}
}
