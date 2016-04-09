package com.yuntongxun.test.ccopfile;


import org.junit.Test;
import com.base.common.Constants;
import com.yuntongxun.test.ccopfile.util.RealTimeUploadFile;
import com.yuntongxun.test.ccopfile.util.SendIMRequestToRest;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class RealTimeUploadIMFile {


	@Test
	public void main() throws KeyManagementException, NoSuchAlgorithmException {
		
		int fileSize = 705573;//705573 bytes mp3 小于100MB
		String fileExt = "mp3";
		String[] uploadInfo = SendIMRequestToRest.getIMRealTimeUploadInfo(fileSize, fileExt);
		String statusCode = uploadInfo[0];
//		String uploadUrl = uploadInfo[1];
		String uploadUrl = Constants.address + "/im/realtimeupload";
		String uploadToken = uploadInfo[2];
		
		if ("000000".equals(statusCode)){
			String relativelyPath = System.getProperty("user.dir");
			String sourcePath = relativelyPath + "\\im\\product\\" + fileSize + "b." + fileExt;
	        String desPath = relativelyPath + "\\im\\product\\upload\\" + fileSize + "b." + fileExt;
			String url = uploadUrl + "?token=" + uploadToken;
			// 发送数据
			RealTimeUploadFile.sendData(sourcePath, desPath, url, fileSize);
		}else {
			System.out.println("REST Return StatusCode = " + statusCode);
		}
	}
}
