package wang.tumbleweed.test.ccopfile;


import org.junit.Test;
import wang.tumbleweed.common.Constants;
import wang.tumbleweed.test.ccopfile.util.DownloadFile;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class RealTimeDownloadIMFile {

	@Test
	public void main() throws KeyManagementException, NoSuchAlgorithmException {
		
		String hostname = Constants.host;
		int port = Integer.parseInt(Constants.port);
		String fileExt = ".mp3";
//		String fileExt = ".exe";
    	String fileName = "fn_2015082415_f1315bc5390144ad91751753380a5b28_3100" + fileExt;
    	String relativelyPath = System.getProperty("user.dir");
		String storeDir = relativelyPath + "\\im\\product\\download";
    	String appName = "/ccopFileServer";
    	//请求URL
		String url = "http://" + hostname + ":" + port + appName + "/imFileRealTimeDownload?fileName=" + fileName;
		
		// 下载数据
		DownloadFile.downloadData(hostname, port, fileName, storeDir, url);
	}
}
