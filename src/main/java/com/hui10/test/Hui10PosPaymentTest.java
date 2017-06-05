package com.hui10.test;

import com.base.common.HttpPostUtil;
import com.base.iso8583.mac.ConvHelper;
import com.enn.test.WebCashEcejTest;
import com.enn.util.DateUtil;
import com.enn.util.DateUtils;
import com.enn.util.MD5SignAndValidate;
import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
        otherAmt.put("body", "7B2263636F6E5F6D6F64223A22303231222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C22636172645F6E6F223A2236323237303032343333333430333537323035222C2270696E5F64617461223A2243434544304538354636414133323445222C227465726D696E616C5F636F6465223A2231323334353637383931222C2274726164655F616D74223A22303030303030303030303031222C2274726164655F6E6F223A22313234323031373035313831343136303530303030303131303838353132222C22747261636B325F6E6F223A22363232373030323433333334303335373230355C75303033643432303335323031303631303230303030222C227265715F74696D65223A223230313730353138313431393538227D");
        otherAmt.put("mac", "3532423233333839");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = devServerUrl + "/" + appId + "/posPay/consume";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

    /**
     * queryOrderInfo
     */
    @Test
    public void queryOrderInfo() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B2263636F6E5F6D6F64223A22303231222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C22636172645F6E6F223A2236323237303032343333333430333537323035222C2270696E5F64617461223A2243434544304538354636414133323445222C227465726D696E616C5F636F6465223A2231323334353637383931222C2274726164655F616D74223A22303030303030303030303031222C2274726164655F6E6F223A22313234323031373035313831343136303530303030303131303838353132222C22747261636B325F6E6F223A22363232373030323433333334303335373230355C75303033643432303335323031303631303230303030222C227265715F74696D65223A223230313730353138313431393538227D");
        otherAmt.put("mac", "3532423233333839");

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
        String body = "7B2268616E6465725F636F6465223A2230303031222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C227265715F6970223A223137322E31362E322E3337222C2273616C74223A226C79396D796833737A6971716E6768647A6B323138356364222C227375625F6D6572635F6964223A22383031323935393638323330393733343530222C227465726D696E616C5F636F6465223A2231323334353637383934222C227465726D696E616C5F6C6F74223A22303030303032222C227265715F74696D65223A223230313730353235313535393230227D";

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
