package wang.tumbleweed.test.base;

import net.sf.json.JSONObject;
import org.apache.commons.digester.Digester;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.ming.sample.util.JSONUtil;
import org.ming.sample.util.ObjectUtil;
import org.ming.sample.xml.XMLUtil;
import org.xml.sax.SAXException;
import wang.tumbleweed.common.Base64;
import wang.tumbleweed.common.MD5;
import wang.tumbleweed.model.*;
import wang.tumbleweed.util.JaxbUtil;
import wang.tumbleweed.util.XStreamUtil;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlTest {

    public Logger log = LogManager.getLogger(XmlTest.class);


    private static final String UTF8 = "utf-8";
    private static final String DES = "DESede";
    // 定义 加密算法,可用 DES,DESede,Blowfish
    private static final String ALGORITHM_DESEDE = "DESede";

    /**
     * 去掉换行符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去掉换行符
     *
     * @param str
     * @return
     */
    public static String replaceBlank2(String str) {
        String dest = "";
        if (str != null) {
            dest = str.replace("\\n", "");
            dest = dest.replace("\\t", "");
            dest = dest.replace("\\r", "");
        }
        return dest;
    }

    @Test
    public void testReplaceBlank() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("TemplateSMS");
        //应用id
        root.addElement("appId").addText("297e7c37444db2f201444db340e40000");

        //1:已发布的app 2:未发表的app  3:网页',
        root.addElement("productType").addText("1");
        //1,3地址
        root.addElement("addr").addText("D:app");

        //模板标题 title
        root.addElement("title").addText("110");
        //签名
        root.addElement("signature").addText("老王的短信模板");

        //模板内容
        root.addElement("templateContent").addText("发送 模板【】sasf\n 爽肤水\r 送到等等\t点对点");

        String body = document.asXML();

        ObjectUtil binder = new ObjectUtil(CreateTemplateSMS.class);

        CreateTemplateSMS createTemplateSMS = binder.fromXml(body);

        log.info(createTemplateSMS.getTemplateContent());

        log.info(XmlTest.replaceBlank(createTemplateSMS.getTemplateContent()));
    }

    @Test
    public void tempReadXML() throws DocumentException {
        File f = new File("src/yun/tong/xun/file/blank.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(f);
        String body = document.asXML();
        ObjectUtil binder = new ObjectUtil(CreateTemplateSMS.class);
        CreateTemplateSMS createTemplateSMS = binder.fromXml(body);
        log.info(createTemplateSMS.getTemplateContent());
        log.info(XmlTest.replaceBlank2(createTemplateSMS.getTemplateContent()));
    }

    @Test
    public void temp() throws DocumentException {
        String  voipAccount = "1234567890";
        voipAccount = voipAccount.substring(0,voipAccount.length() - 8);
        log.info(voipAccount);

    }

    @Test
    public void jsonTestOld() throws DocumentException {
        String value = "{\"accountSid\":\"4028efe33fc65b56013fc65be7cc0000\",\"appId\":\"4028efe33fc65b56013fc660001f0002\",\"appStatus\":\"2\",\"authToken\":\"\",\"balance\":\"\",\"callbackState\":\"0\",\"channel_id\":\"\",\"con_nums\":\"0\",\"dateCreated\":\"\",\"dateUpdated\":\"\",\"friendlyName\":\"\",\"identityType\":\"0\",\"ivrDialState\":\"0\",\"landingCallsState\":\"0\",\"messagesState\":\"0\",\"ratetype\":\"0\",\"rest_call_num\":\"0\",\"status\":\"0\",\"subbalance\":\"\",\"templateSmsState\":\"0\",\"thrTestNum\":\"\",\"totalRecharge\":\"\",\"type\":\"0\",\"uri\":\"\",\"voiceVerifyState\":\"0\",\"voipId\":\"80038200000001\"}";

        log.info("begin old");
        long t1 = System.currentTimeMillis();
        CCPAccount accountOld = (CCPAccount) JSONUtil.jsonToObj(value, CCPAccount.class);
        long t2 = System.currentTimeMillis();
        log.info("end old");
        log.info("old -------" + (t2 - t1));

    }


    @Test
    public void jsonTestNew() throws DocumentException {
        String value = "{\"accountSid\":\"4028efe33fc65b56013fc65be7cc0000\",\"appId\":\"4028efe33fc65b56013fc660001f0002\",\"appStatus\":\"2\",\"authToken\":\"\",\"balance\":\"\",\"callbackState\":\"0\",\"channel_id\":\"\",\"con_nums\":\"0\",\"dateCreated\":\"\",\"dateUpdated\":\"\",\"friendlyName\":\"\",\"identityType\":\"0\",\"ivrDialState\":\"0\",\"landingCallsState\":\"0\",\"messagesState\":\"0\",\"ratetype\":\"0\",\"rest_call_num\":\"0\",\"status\":\"0\",\"subbalance\":\"\",\"templateSmsState\":\"0\",\"thrTestNum\":\"\",\"totalRecharge\":\"\",\"type\":\"0\",\"uri\":\"\",\"voiceVerifyState\":\"0\",\"voipId\":\"80038200000001\"}";
        log.info("begin new");
        long t3 = System.currentTimeMillis();
        CCPAccount accountNew = (CCPAccount) JSONObject.toBean(JSONObject.fromObject(value), CCPAccount.class);
        long t4 = System.currentTimeMillis();
        log.info("end new");
        log.info("new--------" + (t4 - t3));
    }


    @Test
    public void xmlTestOld() throws DocumentException, JAXBException {

        File f = new File("src/yun/tong/xun/file/blank.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(f);
        String value = document.asXML();
        log.info(value);

        log.info("begin old");
        long t1 = System.currentTimeMillis();
        ObjectUtil binder = new ObjectUtil(wang.tumbleweed.model.XmlTest.class);
        Object objectOld = binder.fromXml(value);
        long t2 = System.currentTimeMillis();
        log.info("end old");
        log.info("old -------" + (t2 - t1));


    }

    @Test
    public void xmlTestNew() throws DocumentException, JAXBException {

        File f = new File("src/yun/tong/xun/file/blank.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(f);
        String value = document.asXML();
        log.info(value);

        log.info("begin new");
        long t3 = System.currentTimeMillis();
        JAXBContext ctx = JAXBContext.newInstance(wang.tumbleweed.model.XmlTest.class);
        Unmarshaller marchaller = ctx.createUnmarshaller();
        wang.tumbleweed.model.XmlTest xmlTest = (wang.tumbleweed.model.XmlTest) marchaller.unmarshal(new StringReader(value));
        long t4 = System.currentTimeMillis();
        log.info("end new");
        log.info("new--------" + (t4 - t3));

    }

    @Test
    public void xmlTestSax() throws DocumentException, JAXBException {

        File f = new File("src/yun/tong/xun/file/blank.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(f);
        String value = document.asXML();
        log.info(value);

        log.info("begin new");
        long t3 = System.currentTimeMillis();
        JaxbUtil resultBinder = new JaxbUtil(wang.tumbleweed.model.XmlTest.class, JaxbUtil.CollectionWrapper.class);
        wang.tumbleweed.model.XmlTest xmlTest = resultBinder.fromXml(value);
        long t4 = System.currentTimeMillis();
        log.info("end new");
        log.info("new--------" + (t4 - t3));

    }

    @Test
    public void xmlTestXStream() throws DocumentException, JAXBException {

        File f = new File("src/yun/tong/xun/file/blank.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(f);
        String value = document.asXML();
        log.info(value);

        log.info("begin new");
        long t3 = System.currentTimeMillis();
        wang.tumbleweed.model.XmlTest xmlTest = XStreamUtil.toBean(value, wang.tumbleweed.model.XmlTest.class);
        long t4 = System.currentTimeMillis();
        log.info("end new");
        log.info("new--------" + (t4 - t3));

    }

    @Test
    public void xmlTestDigester() throws DocumentException, JAXBException, IOException, SAXException {

        File f = new File("src/yun/tong/xun/file/blank.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(f);
        String value = document.asXML();
        log.info(value);

        log.info("begin new");
        long t3 = System.currentTimeMillis();
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("TemplateSMS", wang.tumbleweed.model.XmlTest.class);
        digester.addSetProperties("TemplateSMS");
        digester.addBeanPropertySetter("TemplateSMS/templateContent1");
        digester.addBeanPropertySetter("TemplateSMS/templateContent2");
        digester.addBeanPropertySetter("TemplateSMS/templateContent3");
        digester.addBeanPropertySetter("TemplateSMS/templateContent4");
        digester.addBeanPropertySetter("TemplateSMS/templateContent5");
        digester.addBeanPropertySetter("TemplateSMS/templateContent6");
        digester.addBeanPropertySetter("TemplateSMS/templateContent7");
        digester.addBeanPropertySetter("TemplateSMS/templateContent8");
        digester.addBeanPropertySetter("TemplateSMS/templateContent9");
        digester.addBeanPropertySetter("TemplateSMS/templateContent10");
        wang.tumbleweed.model.XmlTest xmlTest = (wang.tumbleweed.model.XmlTest) digester.parse(new StringReader(value));

        long t4 = System.currentTimeMillis();
        log.info("end new");
        log.info("new--------" + (t4 - t3));

    }


    @Test
    public void getString3DDes() {

        String value = XmlTest.desedeEncoder("kaifaopenserpass", "YUNTONGXUN_FS");

        log.info(value);

    }

    @Test
    public void isList() {

        List<String> list = new ArrayList<String>();
        String test = "test";

        Object testOf = list;
        if (testOf instanceof  List) {
            log.info("true");
        } else {
            log.info("false");
        }

    }

    @Test
    public void list() {

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        log.info(list.get(list.size() - 1));

    }


    /**
     * 字节数组转化为大写16进制字符串
     *
     * @param b
     * @return
     */
    private static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 3DES加密
     *
     * @param src
     * @param key
     * @return
     */
    private static String desedeEncoder(String src, String key) {
        SecretKey secretKey;
        try {
            secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] b = cipher.doFinal(src.getBytes(UTF8));
            return byte2HexStr(b);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 构造3DES加解密方法key
     *
     * @param keyStr
     * @return
     * @throws java.io.UnsupportedEncodingException
     * @throws Exception
     */
    private static byte[] build3DesKey(String keyStr) {
        try {
            byte[] key = new byte[24];
            byte[] temp;
            temp = keyStr.getBytes(UTF8);
            if (key.length > temp.length) {
                System.arraycopy(temp, 0, key, 0, temp.length);
            } else {
                System.arraycopy(temp, 0, key, 0, key.length);
            }
            return key;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void statsd() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();

        String body = document.asXML();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.219:8080/2013-12-26/inter/Test/app";

        returnTT(mainAccout, token, url, body);
    }

    public void returnTT(String mainAccout, String token, String url,
                         String body) throws NoSuchAlgorithmException, IOException {
        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String sig = mainAccout + token + curr_date;
        sig = MD5.md5(sig);
        String authorization = mainAccout + ":" + curr_date;
        authorization = Base64.encodeToString(authorization);
        String httpurl = url + "?sig=" + sig;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(httpurl);
        httppost.setHeader("Content-Type", "application/xml;charset=utf-8");
        httppost.setHeader("Accept", "application/xml");
        httppost.setHeader("Authorization", authorization);

        HttpEntity entity = new StringEntity(body, "UTF-8");
        httppost.setEntity(entity);
        HttpResponse httpresponse = httpclient.execute(httppost);
        String conResult = EntityUtils.toString(httpresponse.getEntity());
        StatusLine statusLine = httpresponse.getStatusLine();
        int status = statusLine.getStatusCode();

        log.info("状态:" + status + ";\n返回包体:" + conResult);

    }

    @Test
    public void xmlSet() {
        String xml = "<Response>\n" +
                "  <callSid>1512111518412810000100040000008a</callSid>\n" +
                "  <orderid>CM1000420151211151841100149</orderid>\n" +
                "  <statusCode>000000</statusCode>\n" +
                "</Response>";

        XMLUtil xmlutil = new XMLUtil(xml);
        String state = xmlutil.getChildElement("state").getText();
//        Element orderid_ele = xmlutil.getChildElement("orderid");
        Element orderid_ele123456 = xmlutil.getChildElement("orderid123456");

        if (!state.equals("")) {
            log.info("state");
        }
//        if (orderid_ele != null) {
//            log.info("orderid_ele");
//        }
        if (orderid_ele123456 != null && !orderid_ele123456.getText().equals("")) {
            log.info("orderid_ele123456");
        }

    }

    /**
     * 对象转xml
     *
     * @param obj
     * @throws javax.xml.bind.JAXBException
     */
    public static String Object2Xml(Object obj) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, writer);
        return writer.toString();
    }

    @Test
    public void toXml() throws JAXBException {
        ServerAddrs serverAddrs = new ServerAddrs();
        serverAddrs.setVersion("1");


        ServerAddrModel serverAddrModel = new ServerAddrModel();
        serverAddrModel.setPort(8080);
        serverAddrModel.setHost("172.0.0.1");

        List<ServerAddrModel> list = new ArrayList<ServerAddrModel>();
        list.add(serverAddrModel);
        list.add(serverAddrModel);

        serverAddrs.setConnector(list);

        serverAddrs.setFileServer(list);

        serverAddrs.setLVS(list);

        log.info(Object2Xml(serverAddrs));
    }

    @Test
    public void test1() throws JAXBException {
        String value = "{\"version\":\"7\",\"Connector\":[{\"host\":\"123.57.33.80\",\"port\":\"8085\"},{\"host\":\"123.57.33.80\",\"port\":\"8085\"}],\"LVS\":[{\"host\":\"123.56.135.81\",\"port\":\"8888\"}],\"FileServer\":[{\"host\":\"123.56.135.81\",\"port\":\"8090\"}]}";
//
//        Map<String, Class> classMap = new HashMap<String, Class>();
//        classMap.put("FileServer", ServerAddrModel.class);
//        classMap.put("Connector", ServerAddrModel.class);
//        classMap.put("LVS", ServerAddrModel.class);
        JSONObject jsonObject = JSONObject.fromObject(value);
        log.info(jsonObject.getString("version"));
        List<JSONObject> connector = jsonObject.getJSONArray("Connector");
        log.info("host:" + connector.get(0).getString("host"));
        log.info("port:"+connector.get(0).getInt("port"));
//        ServerAddrs serverAddrs = (ServerAddrs) JSONObject.toBean(jsonObject, ServerAddrs.class, classMap);

    }
}
