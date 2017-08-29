package com.tumbleweed.test.hui10;

import com.tumbleweed.test.base.common.HttpPostUtil;
import com.tumbleweed.test.base.iso8583.mac.ConvHelper;
import com.tumbleweed.test.enn.test.WebCashEcejTest;
import com.tumbleweed.test.enn.util.DateUtils;
import com.tumbleweed.test.enn.util.MD5SignAndValidate;
import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mylover on 4/9/16.
 */
public class Hui10PosPaymentTest {

    private final static Logger log = LoggerFactory.getLogger(WebCashEcejTest.class);

    private static final String localServerUrl = "http://localhost:8080/posPayment";
    private static final String devServerUrl = "http://devPospay.hui10.com:9014/posPayment";
    private static final String testServerUrl = "http://testPospay.hui10.com:8014/posPayment";
    private static final String biztestServerUrl = "http://10.32.15.41:8080/Transaction";
    private static final String prodServerUrl = "http://inpay.local/Transaction";

    /**
     * sign
     */
    @Test
    public void sign() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B227465726D696E616C5F6C6F74223A22303030303033222C2268616E6465725F636F6465223A2277616E676A70222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C227265715F6970223A223132332E31322E31322E313233222C227465726D696E616C5F636F6465223A2231323334353637383931222C227375625F6D6572635F6964223A22383031323935393638323330393733343436222C227265715F74696D65223A223230313730353138313431313333227D");
//        otherAmt.put("mac", "4441334143314238");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = localServerUrl + "/" + appId + "/posPay/signIn";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

    /**
     * unifiedOrder
     */
    @Test
    public void unifiedOrder() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B2274696D655F657870697265223A223230313730353138313531343437222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C227265715F6970223A223132332E31322E31322E313233222C227465726D696E616C5F636F6465223A2231323334353637383931222C227375625F6D6572635F6964223A22383031323935393638323330393733343436222C2263757272656E6379223A22313536222C226D6572635F6F726465725F6E6F223A223132333435363738393031323334353637383930222C227265715F74696D65223A223230313730353138313431343437227D");
        otherAmt.put("mac", "4532343932413331");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = devServerUrl + "/" + appId + "/posPay/unifiedOrder";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }


    /**
     * comsume
     */
    @Test
    public void comsume() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B2263636F6E5F6D6F64223A22303231222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C22636172645F6E6F223A2236323237303032343333333430333537323035222C2270696E5F64617461223A2242373130313643383439324333443143222C227465726D696E616C5F636F6465223A2231323334353637383931222C2274726164655F616D74223A22303030303030303030303031222C2274726164655F6E6F223A22313234323031373035313831343136303530303030303131303838353132222C22747261636B325F6E6F223A22363232373030323433333334303335373230355C75303033643432303335323031303631303230303030222C227265715F74696D65223A223230313730363233313130393332227D");
        otherAmt.put("mac", "3630304330453442");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = localServerUrl + "/" + appId + "/posPay/consume";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

    /**
     * consumeCancel
     */
    @Test
    public void comsumeCancel() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B2263636F6E5F6D6F64223A22303231222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C22636172645F6E6F223A2236323237303032343333333430333537323035222C2270696E5F64617461223A2242373130313643383439324333443143222C227465726D696E616C5F636F6465223A2231323334353637383931222C2274726164655F616D74223A22303030303030303030303031222C2274726164655F6E6F223A22313234323031373035313831343136303530303030303131303838353132222C22747261636B325F6E6F223A22363232373030323433333334303335373230355C75303033643432303335323031303631303230303030222C227265715F74696D65223A223230313730363233313132333139222C22726566756E645F6F726465725F6E6F223A223833386364366437386637373465616561653838373765633166376362393164227D");
        otherAmt.put("mac", "4539363234413438");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = localServerUrl + "/" + appId + "/posPay/consumeCancel";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

    /**
     * queryOrderInfo
     */
    @Test
    public void queryOrderInfo() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B2263636F6E5F6D6F64223A22303231222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C22636172645F6E6F223A2236323237303032343333333430333537323035222C2270696E5F64617461223A2242373130313643383439324333443143222C227465726D696E616C5F636F6465223A2231323334353637383931222C2274726164655F616D74223A22303030303030303030303031222C2274726164655F6E6F223A22313234323031373035313831343136303530303030303131303838353132222C22747261636B325F6E6F223A22363232373030323433333334303335373230355C75303033643432303335323031303631303230303030222C227265715F74696D65223A223230313730363233313130393332227D");
        otherAmt.put("mac", "3630304330453442");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = localServerUrl + "/" + appId + "/posPay/queryOrderInfo";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }


    @Test
    public void testCallBack() {
        String body = "7B22636F72675F64617465223A2230373230222C226368616E6E656C5F6D6572635F6964223A2232393030313030303936222C226368616E6E656C5F6D6572635F6E616D65223A22E5A4A9E4B88AE4BABAE997B4222C22736574746C655F64617465223A223230313730373230222C227465726D696E616C5F636F6465223A2231323334353637383935222C22636F72675F7365715F6C6F74223A22303030303139222C2274726164655F616D74223A22303030303030303030303031222C226175745F636F6465223A2232303137303732303134323831313733353739222C227265715F61646469616C223A22413131313230306D222C22636172645F6578705F64617465223A2232373034222C2274726164655F737473223A2253554343455353222C22636F72675F7365715F6E6F223A22303030323236222C22636172645F6E6F223A2236323130383132343330303238373336323834222C227265745F7066655F6E6F223A22303732303739333237333436222C22636F72675F74696D65223A22313432363536222C2274726164655F6E6F223A22313234323031373037323030323238343930303030303131323139353834222C2263757272656E6379223A22313536222C226D6572635F6F726465725F6E6F223A22323031373037323031343237323038343632333530303030303033303934227D";

        log.info(ConvHelper.HEX2STR(body));
    }

    /**
     * 查询
     */
    @Test
    public void query() throws IOException, NoSuchAlgorithmException, InterruptedException {

        HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "lLUFoQzCSMpZUlOggfQDsboBoh4ceFbx");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_no", "124201705180414430000013316736");

        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "5f6d3a4367a343e69d3ae9487ec45106");

        String body = new Gson().toJson(tradeParamMapWithSign);

        log.info("body:\n" + body);

        String appId = "A10000";

        String token = "5f6d3a4367a343e69d3ae9487ec45106";

        String url = devServerUrl + "/" + appId + "/posPay/queryPosOrderInfo";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

    /**
     * 查询
     */
    @Test
    public void queryRefundInfo() throws IOException, NoSuchAlgorithmException, InterruptedException {

        HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "lLUFoQzCSMpZUlOggfQDsboBoh4ceFbx");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_no", "124201705180414430000013316736");
        tradeParamMap.put("refund_no", "2017062300234123626651648");

        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "5f6d3a4367a343e69d3ae9487ec45106");

        String body = new Gson().toJson(tradeParamMapWithSign);

        log.info("body:\n" + body);

        String appId = "A10000";

        String token = "5f6d3a4367a343e69d3ae9487ec45106";

        String url = localServerUrl + "/" + appId + "/posPay/queryRefundInfo";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }


    /**
     * 生成终端主密钥
     */
    @Test
    public void makeMasterKey() throws IOException, NoSuchAlgorithmException, InterruptedException {

        HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "123456");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("terminal_code", "1234567891");
        tradeParamMap.put("req_ip", "123.12.12.123");

        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "5f6d3a4367a343e69d3ae9487ec45106");

        String body = new Gson().toJson(tradeParamMapWithSign);

        log.info("body:\n" + body);

        String appId = "A10000";

        String token = "5f6d3a4367a343e69d3ae9487ec45106";

        String url = localServerUrl + "/" + appId + "/posPay/makeMasterKey";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }


}
