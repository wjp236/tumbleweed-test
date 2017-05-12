package com.hui10.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class Hui10SmsUtil {

	private static final Logger logger = LoggerFactory.getLogger(Hui10SmsUtil.class);
	
	private static String sendUrl = "http://172.16.254.243:8080//pubservice/sms/send";
	
	public static void sendSms(String systemFlowId, String ip, String clientNo, String content, String phoneNo) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(sendUrl);
		CloseableHttpResponse response = null;

		InputStream inputStream = null;
		String result = null;

		Map<String, String> params = new HashMap<String, String>();
		params.put("content", content);

		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("phone", phoneNo);// 手机号
		jsonObject.put("smstmpl", "smsTpl:46cf21d4-240b-4c3a-bc3c-1efe89aff306");// 模板Id
		jsonObject.put("platfrom", "5"); // 平台
		
		JSONObject jsonObjectBody = new JSONObject();
		
		String content1 = systemFlowId;
		String content2 = ip + "#" + clientNo;
		
		jsonObjectBody.put("content1", content1);
		jsonObjectBody.put("content2", content2);
		
		int length = content.length();
		int j = 3;
		for (int i = 0; i < length;) {
			
			String key = "content" + j;
			String value = content.substring(i, i+50>length?length:i+50);
			jsonObjectBody.put(key, value);
			i = i + 50;
			j++;
		}
		jsonObject.put("params", jsonObjectBody);
		

		StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), "UTF-8");
		stringEntity.setContentEncoding("UTF-8");
		httpPost.setEntity(stringEntity);

		try {
			response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				logger.error("---短信平台返回错误状态码 --->{}", statusCode);
			}

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				inputStream = entity.getContent();
				result = IOUtils.toString(inputStream, "UTF-8");
				logger.info("---短信平台响应信息--->{}", result);
			}

			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException:" + e);
		} catch (IOException e) {
			logger.error("IOException:" + e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				logger.error("IOException:" + e);
			}
		}
	}

    @Test
    public void testSms() {
        Hui10SmsUtil.sendSms("12345678", "172.0.0.1", "", "", "18600200156");
    }

}
