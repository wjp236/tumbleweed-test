package com.yuntongxun.test.ccopfile;


import com.yuntongxun.test.ccopfile.util.UploadFile;
import org.junit.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class VideoConfImageFileUpload {


	@Test
	public void main() throws KeyManagementException, NoSuchAlgorithmException {
		
		String hostname = "192.168.178.219";
		int port = 8080;
		String token = "84d216e579174178ae110603baf2b1fb";// 39381
		String appName = "/ccopFileServer";
		//请求URL
		String url = "http://" + hostname + ":" + port + appName + "/video/upload?token=" + token;
		String relativelyPath = System.getProperty("user.dir");
		String filePath = relativelyPath + "\\im\\product\\33208b.png";// 小于1MB
		
		// 发送数据
		UploadFile.sendData(filePath, url);
	}
}
