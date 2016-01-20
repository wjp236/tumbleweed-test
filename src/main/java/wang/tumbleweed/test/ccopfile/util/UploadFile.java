package wang.tumbleweed.test.ccopfile.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class UploadFile {

	/**
	 * 发送数据
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public static void sendData(String filePath, String url)
	       throws KeyManagementException, NoSuchAlgorithmException {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
    		HttpPost httppost = new HttpPost(url);
    		//指定请求头信息
            httppost.setHeader("Accept", "application/xml");
            httppost.setHeader("Content-Type", "application/octet-stream");
            
            //默认字符编码
            BasicHttpEntity requestBody = new BasicHttpEntity();
            
            fis = new FileInputStream(filePath);
            //打印请求方法、URL以及HTTP协议版本
            System.out.println("Executing request: " + httppost.getRequestLine());

            requestBody.setContent(fis);
            requestBody.setContentLength(fis.available());
            httppost.setEntity(requestBody);
            
            //执行客户端请求
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();//获取响应实体信息
            
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(response.getStatusLine());
            //默认字符编码
            String defaultCharset = "UTF-8";
            if (entity != null) {
            	//打印服务端响应内容的长度
                System.out.println("Response content length: " + entity.getContentLength());
                //打印服务端响应内容
                System.out.println("Response content: " + EntityUtils.toString(entity, defaultCharset));
            }
            EntityUtils.consume(entity);//确保HTTP响应内容全部被读出或者内容流被关闭
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if (fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 关闭连接
            httpclient.getConnectionManager().shutdown();
		}
	}
}
