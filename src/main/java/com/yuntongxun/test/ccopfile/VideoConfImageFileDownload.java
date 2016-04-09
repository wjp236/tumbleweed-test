package com.yuntongxun.test.ccopfile;


import com.yuntongxun.test.ccopfile.util.DownloadFile;
import org.junit.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class VideoConfImageFileDownload {

	@Test
	public void main() throws KeyManagementException, NoSuchAlgorithmException {
		
		String hostname = "121.41.21.227";
		int port = 8710;
		String fileExt = ".png";
    	String fileName = "fn_10006sELGXBXReqf_81652000000009" + fileExt;// 33208 bytes
    	String appName = "/ccopFileServer";
    	//请求URL
		String url = "http://" + hostname + ":" + port + appName + "/videoFileDownload?fileName=" + fileName;
		String relativelyPath = System.getProperty("user.dir");
		String storeDir = relativelyPath + "\\im\\product\\download";
    	
		// 下载数据
		DownloadFile.downloadData(hostname, port, fileName, storeDir, url);
		
	}
}
