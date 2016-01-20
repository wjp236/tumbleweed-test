package wang.tumbleweed.test.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import wang.tumbleweed.common.HttpPostUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mylover on 9/16/15.
 */
public class GeneralTest {

    private static final Logger logger = LogManager.getLogger(GeneralTest.class);

    @Test
    public void generalTest() throws IOException, NoSuchAlgorithmException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Request");
        Element errorCodes = root.addElement("errorCodes");
        errorCodes.addElement("errorCode").addText("111111");
        errorCodes.addElement("errorCode").addText("222222");
        String body = document.asXML();

        String accountSid = "4028efe33fc65b56013fc65be7cc0000";
        String token = "5091250ed5154c31ab286664eed13043";

        String url = "http://192.168.178.219:8080/2013-12-26/General/GetErrorCode";

        HttpPostUtil HttpPostUtil = new HttpPostUtil();

        String s = HttpPostUtil.sendXML(accountSid, token, url, body);

        logger.info(s);
    }


}
