package com.base.test;

import com.alibaba.fastjson.JSON;
import com.base.common.Base64;
import com.base.common.MD5;
import com.base.common.MD5SignAndValidate;
import com.base.model.Model;
import com.base.util.FastJsonUtils;
import com.google.gson.Gson;
import com.yuntongxun.model.CCPAccount;
import com.yuntongxun.model.PushMessage;
import com.yuntongxun.model.Response;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;
import org.ming.sample.util.JSONUtil;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonTest {

    public Logger log = LogManager.getLogger(JsonTest.class);


    @Test
    public void json1() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();

        String body = document.asXML();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.219:8080/2013-12-26/Json/Test/oldJson";

        returnTT(mainAccout, token, url, body);
    }

    @Test
    public void json2() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();

        String body = document.asXML();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.219:8080/2013-12-26/Json/Test/json";

        returnTT(mainAccout, token, url, body);
    }

    @Test
    public void json3() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();

        String body = document.asXML();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.219:8080/2013-12-26/Json/Test/gjson";

        returnTT(mainAccout, token, url, body);
    }


    @Test
    public void json4() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {

        Document document = DocumentHelper.createDocument();

        String body = document.asXML();

        String mainAccout = "4028eb25444d379701444d426e640001";

        String token = "ff7e65d6dfec46cfbf3cf21abdd096d7";

        String url = "http://192.168.178.219:8080/2013-12-26/Json/Test/fastjson";

        returnTT(mainAccout, token, url, body);
    }

    @Test
    public void json5() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        CCPAccount ccpAccount = new CCPAccount();
        ccpAccount.setAppId("123456");
        ccpAccount.setAccountSid("654321");


    }

    @Test
    public void json6() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
    }


    @Test
    public void json7() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        String value = "{\"YTX_REST_QUEUE_WANG\":\"[59101]\", \"YTX_REST_QUEUE_SERVER\":\"[11001]\"}";
        Map<String, List<Integer>> results = null;
        results = (Map<String, List<Integer>>) JSONUtil.jsonToObj(value, Map.class);

    }

    @Test
    public void json8() throws
            NoSuchAlgorithmException, IOException {
        String value = "{\"YTX_REST_QUEUE_WANG\":\"[59101]\", \"YTX_REST_QUEUE_SERVER\":\"[11001]\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> maps = objectMapper.readValue(value, Map.class);
        log.info(maps.get("YTX_REST_QUEUE_SERVER"));

    }

    @Test
    public void json9() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        JSONObject json= new JSONObject();
        json.put("pwd", "yuntongxunytx123");
        log.info(json.toString());
    }

    @Test
    public void json10() {
        Object obj = new Response("112077", "内部错误");
        String body = JSONUtil.bean2json(obj);
        log.info(body);
    }

    @Test
    public void json11() throws
            NoSuchAlgorithmException, IOException, DocumentException, JDOMException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("CallbackAgent");
        Element business = root.addElement("business");
        business.addElement("userid").addText("110");
        business.addElement("storeid").addText("120");
        business.addElement("houseid").addText("130");
        root.addElement("from").addText("01052823175");
        root.addElement("to").addText("18600200156");
        root.addElement("customerSerNum").addText("18210819960");

        String body = document.asXML();
        log.info("value:" + body);
        xml2Json(body);

    }

    @Test
    public void json12() throws IOException {
        String testJson = "{ \"sender\" : \"53feebe00cf2d6351940edc9\" , \"appId\" : \"8a48b5514fd49643014fda46ebdf11e6\" , \"receiver\" : [\"5417f8a8c4aa5f7efdfeef43\"] , \"msgFileName\" : \"\" , \"msgContent\" : \"{bizKey:568dddc20cf21c22840b08fd, msgType:\\\"10\\\", msg:\\\"爱兜捞：那额磨。\\\", msgName:\\\"动态评论\\\"}\" , \"pushType\" : 1 , \"msgFileUrl\" : \"\" , \"msgType\" : \"1\" , \"msgDomain\" : \"{chatting_id:0c606b5446e9788c5959f61734100d2f, user_nickname:爱兜捞, user_avatar:http://120.24.76.144:8080/images/2015/1/24/54c32a880cf27025395f1101}\"}";
        ObjectMapper mapper = new ObjectMapper();
        PushMessage pushMessage = mapper.readValue(testJson, PushMessage.class);
        log.info("msgContent {}",pushMessage.getMsgContent());
    }

    public static String xml2Json(String xml) throws IOException, JDOMException {
        JSONObject obj = new JSONObject();
//        InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
//        SAXBuilder sb = new SAXBuilder();
//        org.jdom2.Document doc = sb.build(is);
//        org.jdom2.Element root = doc.getRootElement();
//        obj.put(root.getName(), iterateElement(root));

        SAXBuilder builder = new SAXBuilder();
        StringReader read = new StringReader(xml);
        InputSource source = new InputSource(read);
        org.jdom2.Document document = builder.build(source);
        org.jdom2.Element root = document.getRootElement();
        obj.put(root.getName(), iterateElement(root));
        return obj.toString();
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

    /**
     * 一个迭代方法
     *
     * @param element
     *            : org.jdom.Element
     * @return java.util.Map 实例
     */
    @SuppressWarnings("unchecked")
    private static Map iterateElement(org.jdom2.Element element) {
        List jiedian = element.getChildren();
        org.jdom2.Element et = null;
        Map obj = new HashMap();
        List list = null;
        for (int i = 0; i < jiedian.size(); i++) {
            list = new LinkedList();
            et = (org.jdom2.Element) jiedian.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
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

    private static final String STR_JSON = "{\"name\":\"Michael\",\"address\":{\"city\":\"Suzou\",\"street\":\" Changjiang Road \",\"postcode\":100025},\"blog\":\"http://www.ij2ee.com\"}";
    public static String xml2JSON(String xml){
        return new XMLSerializer().read(xml).toString();
    }

    public static String json2XML(String json){
        JSONObject jobj = JSONObject.fromObject(json);
        XMLSerializer xmlserializer = new XMLSerializer();
        xmlserializer.setRootName("Request");
        String xml = xmlserializer.write(jobj);
        return xml;
    }

    @Test
    public void json13() {
        JSONObject json = JSONObject.fromObject(STR_JSON);
        String timeoutrecordurl = null;
        try {
            timeoutrecordurl = json.getString("timeoutrecordurl");
        } catch (Exception e) {
            log.info("timeoutrecordurl is null");
        }
        System.out.println(timeoutrecordurl);
    }



    @Test
    public void fastJson() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "zhangsan");
        map.put("age", 24);
        map.put("sex", "男");

        //map集合
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("name", "xiaohong");
        temp.put("age", "23");


        map.put("girlInfo",  JSON.toJSONString(temp));

        Map<String, Object> signData = MD5SignAndValidate.signData(map, "xinyipay");

        String jsonString = JSON.toJSONString(signData);

        log.info("\njsonString:" + jsonString);

        Map<String, Object> resultMap = JSON.parseObject(jsonString);

        log.info(MD5SignAndValidate.validateData(resultMap, "xinyipay"));


        Model model = new Model();
        model.setKey("test");
        model.setValue("testValue");

        String json = JSON.toJSONString(model);

        log.info(json);

        Map model1 = JSON.parseObject(json, Map.class);

    }

    @Test
    public void fastJsonMap() {
        String msg = "{'ccb_payment_bill_switch':'yes'}";
        Map<String, String> keysMap = FastJsonUtils.parseObject(msg, Map.class);

        keysMap.put("ccb_payment_bill_switch", "no");
        keysMap.put("ccb_payment_bill_switch1", "yes");

        Object obj = keysMap;

        log.info("\ncome on:" + FastJsonUtils.toJSONString(obj));

        Set<Map.Entry<String, String>> set = keysMap.entrySet();
        for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

    @Test
    public void listJsonTest() {

        List<Model> modelList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Model model = new Model();
            model.setKey("k" + i);
            model.setValue("v" + i);
            modelList.add(model);
        }

        Map<String, String> resMap = new HashMap<>();
        resMap.put("ret_code", "000000");
        resMap.put("ret_msg", "success");
        resMap.put("refundDetail", new Gson().toJson(modelList));

        log.info(new Gson().toJson(resMap));
    }

}
