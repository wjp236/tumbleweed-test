/**
 * CopyRight 2016 必拓电子商务有限公司
 */
package com.tumbleweed.test.base.util;

import org.apache.http.ssl.SSLContexts;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http帮助类
 */
public class HttpUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    @Test
    public void testHttps() throws NoSuchAlgorithmException, IOException, KeyManagementException {

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.sendHttpsPost("", "", false, 10000);
    }

	private final static int BUFFER_SIZE = 2048;

	/**
	 * https post （作者：wupeng<wupengg@enn.com>）
	 * 
	 * @param url
	 *            发送地址
	 * @param params
	 *            发送参数
	 * @param isDevEnv
	 *            是否免ssl
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public static String sendHttpsPost(String url, String params,
			boolean isDevEnv, int timeOut) throws IOException,
			KeyManagementException, NoSuchAlgorithmException {

		URL connurl = new URL(url);
		HttpsURLConnection conn = null;
		String ret = null;
		OutputStream outStream = null;
		InputStream inStream = null;

		try {
			conn = (HttpsURLConnection) connurl.openConnection();

			// 测试环境忽略SSL证书验证
			if (isDevEnv) {
				ignoreSSLVerify(conn);
			}

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setConnectTimeout(timeOut); // 设置连接超时为 timeOut s
			conn.setReadTimeout(timeOut); // 读取数据超时为 timeOut s

			outStream = conn.getOutputStream();
			outStream.write(params.getBytes("UTF-8"));
			outStream.flush();

			inStream = conn.getInputStream();
			byte[] bin = readInputStream(inStream);

			ret = new String(bin, "UTF-8");
		} finally {
			if (inStream != null)
				inStream.close();
			if (outStream != null)
				outStream.close();
			if (conn != null)
				conn.disconnect();
		}
		return ret;
	}

	public static String sendHttpPost(String urlString, String requestBody,
			int timeOut) throws IOException {

		HttpURLConnection httpConn = null;
		String ret = null;
		OutputStream outStream = null;
		InputStream inStream = null;

		try {
			URL url = new URL(urlString);
			httpConn = getConnection(url, "POST", timeOut, null);
			httpConn.connect();

			outStream = httpConn.getOutputStream();
			outStream.write(requestBody.getBytes("UTF-8"));
			outStream.flush();

			inStream = httpConn.getInputStream();
			byte[] bin = readInputStream(inStream);

			ret = new String(bin, "UTF-8");
		} finally {
			if (inStream != null)
				inStream.close();
			if (outStream != null)
				outStream.close();
			if (httpConn != null)
				httpConn.disconnect();
		}
		return ret;
	}

	public static String sendHttpPost(String urlString, String requestBody,
			int timeOut, String encoding, String contentType)
			throws IOException {

		HttpURLConnection httpConn = null;
		String ret = null;
		OutputStream outStream = null;
		InputStream inStream = null;

		try {
			URL url = new URL(urlString);
			httpConn = getConnection(url, "POST", timeOut, contentType);
			httpConn.connect();

			outStream = httpConn.getOutputStream();
			outStream.write(requestBody.getBytes(encoding));
			outStream.flush();

			inStream = httpConn.getInputStream();
			byte[] bin = readInputStream(inStream);

			ret = new String(new String(bin, encoding).getBytes("UTF-8"),
					"UTF-8");

		} finally {
			if (inStream != null)
				inStream.close();
			if (outStream != null)
				outStream.close();
			if (httpConn != null)
				httpConn.disconnect();
		}
		return ret;
	}

	/**
	 * http get （作者：wupeng<wupengg@enn.com>）
	 * 
	 * @param urlString
	 * @param timeOut
	 * @return
	 */
	public static byte[] getUrlFileData(String urlString, int timeOut) {
		if (!checkData(urlString)) {
			return null;
		}

		InputStream inputStream = null;
		ByteArrayOutputStream outStream = null;
		HttpURLConnection httpConn = null;
		try {
			URL url = new URL(urlString);
			httpConn = getConnection(url, "GET", timeOut, null);
			httpConn.connect();
			inputStream = httpConn.getInputStream();
			outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			byte[] fileData = outStream.toByteArray();
			return fileData;
		} catch (SocketTimeoutException e) {
			logger.error("请求超时！url：" + urlString, e);
			return "TIMEOUT".getBytes();
		} catch (IOException e) {
			logger.error("the data stream error！", e);
		} catch (Exception e) {
			if (httpConn != null) {
				try {
					logger.error("请求失败！发生异常！url:" + urlString + "！请求状态码："
							+ httpConn.getResponseCode(), e);
				} catch (IOException e1) {
					logger.error("获取http请求状态码失败", e1);
				}
			} else {
				logger.error("请求失败！发生异常！url:" + urlString, e);
			}
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}

				if (outStream != null) {
					outStream.close();
				}

				if (httpConn != null) {
					httpConn.disconnect();
				}
			} catch (IOException e) {
				logger.error("close data stream error！", e);
			}
		}

		return null;
	}

	/**
	 * http post （作者：wupeng<wupengg@enn.com>）
	 * 
	 * @param urlString
	 * @param params
	 * @param timeOut
	 * @return
	 */
	public static byte[] postUrlFileData(String urlString,
			Map<String, String> params, int timeOut) {
		if (!checkData(urlString)) {
			return null;
		}

		InputStream inputStream = null;
		ByteArrayOutputStream outStream = null;
		OutputStreamWriter out = null;
		HttpURLConnection httpConn = null;
		try {
			String content = buildRequestParams(params);
			URL url = new URL(urlString);
			httpConn = getConnection(url, "POST", timeOut, null);
			httpConn.connect();
			// 读和写流都创建,写流必须在读流之前,并且写流先给服务器发消息,不然创建了读流后发送数据,服务器会收不到.
			out = new OutputStreamWriter(httpConn.getOutputStream());
			out.write(content);
			out.flush();
			inputStream = httpConn.getInputStream();
			outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			if (inputStream != null) {
				while ((length = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, length);
				}
			}

			byte[] fileData = outStream.toByteArray();
			return fileData;
		} catch (SocketTimeoutException e) {
			logger.error("请求超时！url：" + urlString, e);
			return "TIMEOUT".getBytes();
		} catch (IOException e) {
			logger.error("the data stream error！", e);
		} catch (Exception e) {
			if (httpConn != null) {
				try {
					logger.error("请求失败！发生异常！url:" + urlString + "！请求状态码："
							+ httpConn.getResponseCode(), e);
				} catch (IOException e1) {
					logger.error("获取http请求状态码失败", e1);
				}
			} else {
				logger.error("请求失败！发生异常！url:" + urlString, e);
			}
		} finally {
			try {
				if (out != null) {
					out.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}

				if (outStream != null) {
					outStream.close();
				}

				if (httpConn != null) {
					httpConn.disconnect();
				}
			} catch (IOException e) {
				logger.error("close data stream error！", e);
			}
		}

		return null;
	}

	// Stitching parameters
	public static String buildRequestParams(Map<String, String> params)
			throws UnsupportedEncodingException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		List<Map.Entry<String, String>> newParams = new ArrayList<>(
				params.entrySet());
		StringBuilder query = new StringBuilder();
		boolean hasParam = false;
		for (Map.Entry<String, String> entry : newParams) {
			String name = entry.getKey();
			String value = entry.getValue();

			// 忽略参数名或参数值为空的参数
			if (areNotEmpty(name, value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}

				query.append(name).append("=").append(value);
			}

		}

		return query.toString();
	}

	/**
	 * http connection （作者：wupeng<wupengg@enn.com>）
	 * 
	 * @param url
	 * @param method
	 * @param timeOut
	 * @return
	 * @throws IOException
	 */
	private static HttpURLConnection getConnection(URL url, String method,
			int timeOut, String contentType) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		if (contentType != null && contentType != "") {
			conn.setRequestProperty("Content-Type", contentType);
		} else {
			conn.setRequestProperty("Content-type", "text/html");
		}
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("contentType", "utf-8");
		conn.setConnectTimeout(timeOut); // 设置连接超时为 timeOut s
		conn.setReadTimeout(timeOut); // 读取数据超时为 timeOut s
		return conn;
	}

	private static boolean areNotEmpty(String name, String value) {
		return !(name == null || value == null || name.equals("") || value
				.equals(""));
	}

	// 检查数据合法性
	public static boolean checkData(String urlString) {
		return urlString != null && urlString.startsWith("http://")
				|| urlString != null && urlString.startsWith("https://");
	}

	private static byte[] readInputStream(InputStream inStream)
			throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = null;
		byte[] buffer = new byte[BUFFER_SIZE];
		int len = 0;
		try {
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			data = outStream.toByteArray();
		} finally {
			if (outStream != null)
				outStream.close();
		}
		return data;
	}

	private static void ignoreSSLVerify(HttpsURLConnection conn)
			throws NoSuchAlgorithmException, KeyManagementException {

		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());

		conn.setSSLSocketFactory(sc.getSocketFactory());

		conn.setHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
	}

	/**
	 * https post （作者：wupeng<wupengg@enn.com>）
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param isDevEnv
	 *            是否免ssl
	 * @param timeOut
	 *            超时时间
	 * @param keyUrl
	 *            证书key地址
	 * @param keyPasswrd
	 *            证书密钥
	 * @return
	 * @throws Exception
	 */
	public static String sendHttpsPost(String url, String params,
			boolean isDevEnv, int timeOut, String keyUrl, String keyPasswrd)
			throws Exception {

		URL connurl = new URL(url);
		HttpsURLConnection conn = null;
		String ret = null;
		OutputStream outStream = null;
		InputStream inStream = null;

		try {
			conn = (HttpsURLConnection) connurl.openConnection();

			// 测试环境忽略SSL证书验证
			if (isDevEnv) {
				ignoreSSLVerify(conn);
			} else {
				KeyStore keyStore = KeyStore.getInstance("PKCS12");
				FileInputStream instream = new FileInputStream(new File(keyUrl));
				try {
					keyStore.load(instream, keyPasswrd.toCharArray());
				} finally {
					instream.close();
				}

				// Trust own CA and all self-signed certs
				SSLContext sslcontext = SSLContexts.custom()
						.loadKeyMaterial(keyStore, keyPasswrd.toCharArray())
						.build();

				conn.setSSLSocketFactory(sslcontext.getSocketFactory());
			}

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setConnectTimeout(timeOut); // 设置连接超时为 timeOut s
			conn.setReadTimeout(timeOut); // 读取数据超时为 timeOut s

			outStream = conn.getOutputStream();
			outStream.write(params.getBytes("UTF-8"));
			outStream.flush();

			inStream = conn.getInputStream();
			byte[] bin = readInputStream(inStream);

			ret = new String(bin, "UTF-8");
		} finally {
			if (inStream != null)
				inStream.close();
			if (outStream != null)
				outStream.close();
			if (conn != null)
				conn.disconnect();
		}
		return ret;
	}
}
