package com.tumbleweed.test.yuntongxun.test.ccopfile.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayInputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;

/**
 * <p>Title: HttpClient</p>
 * <p>Description: HttpClient</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: hisunsray</p>
 * <p>Date: 2012-09-18</p>
 * @author tanglujun
 * @version 1.0
 */
public class HttpClient {

	/**
	 * 注册SSL连接
	 * 
	 * @param hostname
	 *            请求的主机名（IP或者域名）
	 * @param protocol
	 *            请求协议名称（TLS-安全传输层协议）
	 * @param port
	 *            端口号
	 * @param scheme
	 *            协议名称
	 * @return HttpClient实例
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public DefaultHttpClient registerSSL(String hostname, String protocol,
			int port, String scheme) throws NoSuchAlgorithmException,
			KeyManagementException {

		// 创建一个默认的HttpClient
		PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(100);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		DefaultHttpClient httpclient = new DefaultHttpClient(cm);

		// 创建SSL上下文实例
		SSLContext ctx = SSLContext.getInstance(protocol);
		// 服务端证书验证
		X509TrustManager tm = new X509TrustManager() {

			/**
			 * 验证客户端证书
			 */
			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType)
					throws CertificateException {
				// 这里跳过客户端证书 验证
			}

			/**
			 * 验证服务端证书
			 *
			 * @param chain
			 *            证书链
			 * @param authType
			 *            使用的密钥交换算法，当使用来自服务器的密钥时authType为RSA
			 */
			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType)
					throws CertificateException {
				if (chain == null || chain.length == 0)
					throw new IllegalArgumentException(
							"null or zero-length certificate chain");
				if (authType == null || authType.length() == 0)
					throw new IllegalArgumentException(
							"null or zero-length authentication type");

				boolean br = false;
				Principal principal = null;
				for (X509Certificate x509Certificate : chain) {
					principal = x509Certificate.getSubjectX500Principal();
					if (principal != null) {
						br = true;
						return;
					}
				}
				if (!br) {
					throw new CertificateException("服务端证书验证失败！");
				}
			}

			/**
			 * 返回CA发行的证书
			 */
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};

		// 初始化SSL上下文
		ctx.init(null, new TrustManager[] { tm },
				new java.security.SecureRandom());
		// 创建SSL连接
		SSLSocketFactory socketFactory = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme sch = new Scheme(scheme, port, socketFactory);
		// 注册SSL连接
		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		return httpclient;
	}

	/**
	 * 发送POST请求
	 * @param hostname 主机名
	 * @param port 端口
	 * @param url 请求URL
	 * @param auth 账号认证信息
	 * @param body 请求BODY
	 * @return [0] - REST返回状态码
	 *         [1] - 文件服务器上传URL
	 *         [2] - 文件服务器上传TOKEN
	 * @throws java.security.KeyManagementException
	 * @throws java.security.NoSuchAlgorithmException
	 */
	@SuppressWarnings("unchecked")
	public String[] postRequest(String hostname, int port, String url,
			String auth, String body) throws KeyManagementException,
			NoSuchAlgorithmException {

		String[] uploadInfo = new String[3];
		String protocol = "TLS";// 请求协议名称（TLS-安全传输层协议）
		String scheme = "https";// 安全协议名称
		DefaultHttpClient httpclient = registerSSL(hostname, protocol,port, scheme);
		String statusCode = "";// REST返回状态码
        String uploadUrl = "";// 文件服务器上传URL
        String uploadToken = "";// 文件服务器上传TOKEN
		try {
			HttpPost httppost = new HttpPost(url);
			// 指定请求头信息
			httppost.setHeader("Accept", "application/xml");
			httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
			httppost.setHeader("Authorization", auth);// base64(子账户Id + 冒号 +
														// 时间戳)
			// 打印请求方法、URL以及HTTP协议版本
			System.out.println("Executing request: "
					+ httppost.getRequestLine());
			System.out.println("Request body: " + body);

			// 默认字符编码
			String defaultCharset = "UTF-8";
			byte[] charsetBody = body.getBytes(defaultCharset);
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(charsetBody));
			requestBody.setContentLength(charsetBody.length);
			httppost.setEntity(requestBody);

			// 执行客户端请求
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();// 获取响应实体信息

			System.out.println("------------------------------------------------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null && response.getStatusLine().getStatusCode() == 200) {
				// 打印服务端响应内容的长度
				System.out.println("Response content length: "
						+ entity.getContentLength());
				
				//打印服务端响应内容
                String reponseXml = EntityUtils.toString(entity, defaultCharset);
                System.out.println("Response content: " + reponseXml);
                Document document = DocumentHelper.parseText(reponseXml);
                Element root = document.getRootElement();
                statusCode = root.elementTextTrim("statusCode");
                System.out.println("parse reponse xml begin...");
                System.out.println("statusCode = " + statusCode);
                if ("000000".equals(statusCode)){
                	for (Iterator i = root.elementIterator("InstanceMessage");i.hasNext();){
            			Element el1 = (Element)i.next();
            			uploadUrl = el1.elementTextTrim("uploadUrl");
            			System.out.println("uploadUrl = " + uploadUrl);
            			uploadToken = el1.elementTextTrim("uploadToken");
            			System.out.println("uploadToken = " + uploadToken);
                	}
                }else {
                	String statusMsg = root.elementTextTrim("statusMsg");
                    System.out.println("statusMsg = " + statusMsg);
                }
                uploadInfo[0] = statusCode;
                uploadInfo[1] = uploadUrl;
                uploadInfo[2] = uploadToken;
			}
			EntityUtils.consume(entity);// 确保HTTP响应内容全部被读出或者内容流被关闭
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			httpclient.getConnectionManager().shutdown();
		}
		return uploadInfo;
	}
}
