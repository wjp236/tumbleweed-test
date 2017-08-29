package com.tumbleweed.test.yuntongxun.test.ccopfile;

import com.tumbleweed.test.yuntongxun.test.ccopfile.util.DownloadFile;
import org.junit.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class NormalDownloadIMFile {

	@Test
	public void main() throws KeyManagementException, NoSuchAlgorithmException {
		
		String hostname = "192.168.178.219";
		int port = 8080;
//		String fileExt = ".mp3";
//		String fileExt = ".exe";
		String fileExt = ".png";
//		String fileExt = ".gif";
    	String fileName = "33208b" + fileExt;
    	String relativelyPath = System.getProperty("user.dir");
		String storeDir = relativelyPath + "\\im\\product\\download";

		String appName = "/ccopFileServer";

    	//请求URL
		String url = "http://" + hostname + ":" + port + appName + "/imFileDownload?fileName=" + fileName;
		
    	// 下载数据
		DownloadFile.downloadData(hostname, port, fileName, storeDir, url);
		
	}
}
