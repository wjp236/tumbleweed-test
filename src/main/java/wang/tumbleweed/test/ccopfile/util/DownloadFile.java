package wang.tumbleweed.test.ccopfile.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class DownloadFile {

	/**
	 * 下载数据
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public static void downloadData(String hostname, int port , String fileName, String storeDir, String url)
	       throws KeyManagementException, NoSuchAlgorithmException {
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
    		HttpGet httpget = new HttpGet(url);
    		// 指定请求头信息
    		httpget.setHeader("Accept", "application/octet-stream");
    		
            // 打印请求方法、URL以及HTTP协议版本
            System.out.println("Executing request: " + httpget.getRequestLine());

            // 执行客户端请求
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();// 获取响应实体信息
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(response.getStatusLine());
            long contentLen = 0L;
            long downloadLen = 0L;
            if (entity != null && response.getStatusLine().getStatusCode() == 200) {
                fis = entity.getContent();
                contentLen = entity.getContentLength();
                if (contentLen != -1){
                	System.out.println("normal download, file length: " + contentLen + " bytes");
                }else {
                	System.out.println("real time download");
                }
            	String pathname = storeDir + "\\" + fileName;
            	System.out.println("download file dir = " + pathname);
            	File downloadFile = new File(storeDir);
				if (!downloadFile.exists()) {
					downloadFile.mkdirs();
			    }
                fos = new FileOutputStream(pathname);
            	byte[] buf = new byte[1024];
            	int len = 0;
            	while ((len = fis.read(buf)) != -1){
            		fos.write(buf, 0, len);
            		downloadLen += len;
            	}
            	if (downloadLen > 0 && contentLen > 0 && downloadLen == contentLen){
            		System.out.println(fileName + " downloaded " + downloadLen + " bytes,finished");
            	}
            	if (contentLen == -1){
            		System.out.println(fileName + " downloaded " + downloadLen + " bytes,finished");
            	}
            }
            EntityUtils.consume(entity);// 确保HTTP响应内容全部被读出或者内容流被关闭
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 关闭连接
            httpclient.getConnectionManager().shutdown();
		}
	}
}
