package com.tumbleweed.test.yuntongxun.test.ccopfile;


import com.tumbleweed.test.yuntongxun.test.ccopfile.util.DownloadFile;
import org.junit.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class PCPluginFileDownload {

	@Test
	public void main() throws KeyManagementException, NoSuchAlgorithmException {
		
		String hostname = "192.168.178.219";
		int port = 8080;
		String fileExt = ".EXE";
    	String fileName = "CCP_PLUGIN_WEB_1.0.0.6" + fileExt;// 2112705 bytes
    	String relativelyPath = System.getProperty("user.dir");
		String storeDir = relativelyPath + "\\im\\product\\download";
		String appName = "/ccopFileServer";
    	//请求URL
		String url = "http://" + hostname + ":" + port + appName + "/pcPluginUpdate?fileName=" + fileName;
    	
		// 下载数据
		DownloadFile.downloadData(hostname, port, fileName, storeDir, url);
	}
}
