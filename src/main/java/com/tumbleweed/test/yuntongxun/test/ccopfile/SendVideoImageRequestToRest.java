package com.tumbleweed.test.yuntongxun.test.ccopfile;

import com.tumbleweed.test.base.common.Constants;
import com.tumbleweed.test.yuntongxun.test.ccopfile.util.DateUtil;
import com.tumbleweed.test.yuntongxun.test.ccopfile.util.EncryptUtil;
import com.tumbleweed.test.yuntongxun.test.ccopfile.util.HttpClient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class SendVideoImageRequestToRest {


	public static void main(String[] args) throws Exception {
		
		//创建房间
		createVideoRoom();
		
		//获取视频头像上传信息
//		getVideoImageUploadInfo();
	}
	
	/**
	 * 视频群聊 - 创建房间
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public static void createVideoRoom() throws KeyManagementException, NoSuchAlgorithmException {

        try {

			String subAccountSid = Constants.subAccountSid;//子账户Id
			String subAuthToken = Constants.subAuthToken;//子账户授权令牌（密码）
    		String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);//时间戳
    		String sig = subAccountSid + subAuthToken + timestamp;//MD5(子账户Id + 子账户授权令牌 + 时间戳)
    		EncryptUtil eu = new EncryptUtil();
    		String signature = eu.md5Digest(sig);
    		//请求URL
    		String url = Constants.restAddress + "/SubAccounts/" + subAccountSid + "/video/create?sig=" + signature;

    		String src = subAccountSid+":"+timestamp;
            String auth = eu.base64Encoder(src);

            String appId = Constants.appId;
            int square = 2;
            String roomName = "video room";
            String body = "<?xml version='1.0' encoding='utf-8'?><VideoConf><appId>" + appId + "</appId><square>" + square + "</square>" +
            		      "<roomName>" + roomName + "</roomName></VideoConf>";

            // 发送POST请求
            HttpClient httpClient = new HttpClient();
            httpClient.postRequest(Constants.restHost, Constants.restPort,url,auth,body);

        }catch(Exception e){
           e.printStackTrace();
        }
	}

	/**
	 * 获取视频头像上传信息
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public static void getVideoImageUploadInfo() throws KeyManagementException, NoSuchAlgorithmException {
		
        try {

        	String subAccountSid = "000000003e1cc9ca013e2219444c0024";//子账户Id
    		String subAuthToken = "3342ba5446874114b18c2ddda4c200ad";//子账户授权令牌（密码）
    		String timestamp = DateUtil.dateToStr(new Date(),DateUtil.DATE_TIME_NO_SLASH);//时间戳
    		String sig = subAccountSid + subAuthToken + timestamp;//MD5(子账户Id + 子账户授权令牌 + 时间戳)
    		EncryptUtil eu = new EncryptUtil();
    		String signature = eu.md5Digest(sig);
    		//请求URL
    		String url = Constants.restAddress + "/SubAccounts/" + subAccountSid + "/video/upload?sig=" + signature;
    		
    		String src = subAccountSid+":"+timestamp;
            String auth = eu.base64Encoder(src);
            
            int fileSize = 70573;//文件大小，单位：字节
            String fileExt = "jpg";
            String roomId = "conf400100019860002832";
            String body = "<?xml version='1.0' encoding='utf-8'?><VideoConf><fileSize>" + fileSize + "</fileSize><roomId>" + roomId + "</roomId>" +
            		      "<fileExt>" + fileExt + "</fileExt></VideoConf>";
        	
            // 发送POST请求
            HttpClient httpClient = new HttpClient();
            httpClient.postRequest(Constants.restHost, Constants.restPort,url,auth,body);
            
        }catch(Exception e){
           e.printStackTrace();
        }
	}
}
