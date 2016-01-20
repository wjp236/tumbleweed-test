package wang.tumbleweed.test.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Test;
import org.ming.sample.util.StreamUtil;
import wang.tumbleweed.common.Base64;
import wang.tumbleweed.common.MD5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EsbTest {

    public Logger logger = LogManager.getLogger(EsbTest.class);

    @Test
    public void fileUpload() throws IOException, NoSuchAlgorithmException {
        Document document = DocumentHelper.createDocument();
        String body = document.asXML();

        String mainAccount = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.177.108:8080/2013-12-26/Accounts/"+ mainAccount +"/Calls/MediaFileUpload";// 本地

        returnTT(mainAccount, token, url, "4028efe33fc65b56013fc660001f0002");

    }

    @Test
    public void fileUpload55() throws IOException, NoSuchAlgorithmException {
        Document document = DocumentHelper.createDocument();
        String body = document.asXML();

        String mainAccount = "4aea47a551a5017f0151a550f4100000";
        String token = "12b62a38a74844a5ad608bdf61d762c0";

        String url = "http://210.72.224.55:8881/2013-12-26/Accounts/"+ mainAccount +"/Calls/MediaFileUpload";// 本地

        returnTT(mainAccount, token, url, "4aea47a551a5017f0151a550f4370001");

    }

    public void returnTT(String mainAccout, String token, String url,
                         String appId) throws NoSuchAlgorithmException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        logger.info(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        String httpurl = url + "?sig=" + sig + "&filename=temp.jpg&appid=" +appId;
        logger.info(httpurl);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);
        httppost.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
        httppost.setHeader("Accept", "application/xml;");
        httppost.setHeader("Authorization", authorization);

        BasicHttpEntity entity = null;

        File file = new File("/Users/mylover/Downloads/temp.jpg");

        if (file.exists()) {
            InputStream is = new FileInputStream(file);
            entity = new BasicHttpEntity();
            entity.setContent(is);
            entity.setContentLength(is.available());
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);
            int status = response.getStatusLine().getStatusCode();
            logger.info(status + "------" +
                    StreamUtil.readContentByStream(response.getEntity().getContent()));
            EntityUtils.consume(response.getEntity());
        }
        httpclient.getConnectionManager().shutdown();

    }
}
