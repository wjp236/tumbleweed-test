package com.enn.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SMSUtils {

	private static String password = "123456";

	private static String username = "shangwu";

	private static String url = "http://10.36.128.10/mdwebservice.asmx/SendSms";

	private static String mobile = "18600200156";

	private static final Logger logger = LoggerFactory
			.getLogger(SMSUtils.class);

	public static void sendSms(String content) {
		String digestPassword = password;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] results = md.digest(password.getBytes());
			String result = StringUtils.bytesToHexString(results);
			digestPassword = result.toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			logger.error("没有MD5加密方法" + e);
		}
		try {
			content = URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("没有utf-8转换格式" + e);
		}

		String sendUrl = url + "?username=" + username + "&password="
				+ digestPassword + "&mobile=" + mobile + "&content=" + content
				+ "&ext=&schtime=&rrid=";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(sendUrl);
		CloseableHttpResponse response1 = null;

		try {
			response1 = httpclient.execute(httpGet);
			HttpEntity entity1 = response1.getEntity();
			EntityUtils.consume(entity1);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException:" + e);
		} catch (IOException e) {
			logger.error("IOException:" + e);
		} finally {
			try {
				response1.close();
			} catch (IOException e) {
				logger.error("IOException:" + e);
			}
		}
	}

	public static void sendSms(String content, String phoneNum) {
		String digestPassword = password;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] results = md.digest(password.getBytes());
			String result = StringUtils.bytesToHexString(results);
			digestPassword = result.toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			logger.error("没有MD5加密方法" + e);
		}
		try {
			content = URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("没有utf-8转换格式" + e);
		}

		String sendUrl = url + "?username=" + username + "&password="
				+ digestPassword + "&mobile=" + phoneNum + "&content="
				+ content + "&ext=&schtime=&rrid=";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(sendUrl);
		CloseableHttpResponse response1 = null;

		try {
			response1 = httpclient.execute(httpGet);
			HttpEntity entity1 = response1.getEntity();
			EntityUtils.consume(entity1);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException:" + e);
		} catch (IOException e) {
			logger.error("IOException:" + e);
		} finally {
			try {
				response1.close();
			} catch (IOException e) {
				logger.error("IOException:" + e);
			}
		}
	}
}
