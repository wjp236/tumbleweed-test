package com.tumbleweed.test.yuntongxun.test.ccopfile;

import com.tumbleweed.test.yuntongxun.test.ccopfile.util.SendIMRequestToRest;
import com.tumbleweed.test.yuntongxun.test.ccopfile.util.UploadFile;
import org.junit.Test;
import com.tumbleweed.test.base.common.Constants;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class NormalUploadIMFile {


	@Test
	public void main() throws KeyManagementException, NoSuchAlgorithmException {
		
		int fileSize = 705573;// 705573 bytes mp3 小于100MB
		String fileExt = "mp3";
		String[] uploadInfo = SendIMRequestToRest.getIMNormalUploadInfo(fileSize, fileExt);
		String statusCode = uploadInfo[0];
//		String uploadUrl = uploadInfo[1];
		String uploadUrl = Constants.address + "/im/normalupload";
		String uploadToken = uploadInfo[2];
		
		if ("000000".equals(statusCode)){
			String relativelyPath = System.getProperty("user.dir");
			String filePath = relativelyPath + "/im/product/" + fileSize + "b." + fileExt;
			String url = uploadUrl + "?token=" + uploadToken + "&isEncrypt=0";
			// 发送数据
			UploadFile.sendData(filePath, url);
		}else {
			System.out.println("REST Return StatusCode = " + statusCode);
		}
	}

}
