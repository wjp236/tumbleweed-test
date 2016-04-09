package com.yuntongxun.test.ccopfile.util;


import com.base.common.EncryptUtil;
import com.base.common.Constants;
import com.yuntongxun.util.DateUtil;
import com.yuntongxun.util.StringUtil;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class SendIMRequestToRest {
	
	public static void main(String[] args) throws Exception {
		
	}
	
	/**
	 * 获取IM控件上传信息
	 * @param fileSize 上传文件大小，单位：字节
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public static String[] getIMControlUploadInfo(int fileSize, String fileExt) throws KeyManagementException, NoSuchAlgorithmException {
		int type = 2;
		return getIMUploadInfo(type, fileSize, fileExt);
	}

	/**
	 * 获取IM实时上传信息
	 * @param fileSize 上传文件大小，单位：字节
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public static String[] getIMRealTimeUploadInfo(int fileSize, String fileExt) throws KeyManagementException, NoSuchAlgorithmException {
		int type = 1;
		return getIMUploadInfo(type, fileSize, fileExt);
	}

	/**
	 * 获取IM正常上传信息
	 * @param fileSize 上传文件大小，单位：字节
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public static String[] getIMNormalUploadInfo(int fileSize, String fileExt) throws KeyManagementException, NoSuchAlgorithmException {
		int type = 0;
		return getIMUploadInfo(type, fileSize, fileExt);
	}

	/**
	 * 获取IM上传信息
	 * @param type 0:正常上传，1：边录边传，2：html+js控件上传
	 * @param fileSize 上传文件大小，单位：字节
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public static String[] getIMUploadInfo(int type, int fileSize, String fileExt) throws KeyManagementException, NoSuchAlgorithmException {
		
		String[] uploadInfo = new String[3];

        try {

        	String subAccountSid = Constants.subAccountSid;//子账户Id
    		String subAuthToken = Constants.subAuthToken;//子账户授权令牌（密码）
    		String timestamp = com.yuntongxun.util.DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);//时间戳
    		String sig = subAccountSid + subAuthToken + timestamp;//MD5(子账户Id + 子账户授权令牌 + 时间戳)
    		com.base.common.EncryptUtil eu = new EncryptUtil();
    		String signature = eu.md5Digest(sig);
    		//请求URL
    		String url = Constants.restAddress + "/SubAccounts/" + subAccountSid + "/IM/SendMsg?sig=" + signature;
    		
    		String src = subAccountSid+":"+timestamp;
            String auth = eu.base64Encoder(src);
    		
    		String msgId = StringUtil.getUUID();//消息Id
    		String from = "80038200000001";//发送端SIP号
            String to = "80038200000002";//接收端SIP号
            String userData = "1234567890";
            String body = "<?xml version='1.0' encoding='utf-8'?><InstanceMessage><msgId>" + msgId + "</msgId><sender>" + from + "</sender>" +
            		      "<receiver>" + to + "</receiver><fileSize>" + fileSize + "</fileSize><type>" + type + "</type>" +
            		      "<fileExt>" + fileExt + "</fileExt><userData>" + userData + "</userData></InstanceMessage>";
            
            // 发送POST请求
            HttpClient httpClient = new HttpClient();
            uploadInfo = httpClient.postRequest(Constants.restHost, Constants.restPort, url, auth, body);
            
        }catch(Exception e){
           e.printStackTrace();
        }
        return uploadInfo;
	}
}
