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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mylover on 4/9/16.
 */
public class Hui10PosPaymentTest {

    private final static Logger log = LoggerFactory.getLogger(WebCashEcejTest.class);

    private static final String localServerUrl = "http://localhost:8080/posPayment";
    private static final String devServerUrl = "http://devPospay.hui10.com:9014/posPayment";
    private static final String testServerUrl = "http://10.32.32.33:9003/webCash";
    private static final String biztestServerUrl = "http://10.32.15.41:8080/Transaction";
    private static final String prodServerUrl = "http://inpay.local/Transaction";

    /**
     * sign
     */
    @Test
    public void sign() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B2274696D655F657870697265223A223230313730353038313133393033222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C227265715F6970223A223132332E31322E31322E313233222C227465726D696E616C5F636F6465223A2231323334353637383931222C227375625F6D6572635F6964223A223830313534363336323734363838303032222C2263757272656E6379223A22313536222C226D6572635F6F726465725F6E6F223A223132333435363738393031323334353637383930222C227265715F74696D65223A223230313730353038313033393033227D");
        otherAmt.put("mac", "4441334143314238");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = devServerUrl + "/" + appId + "/posPay/signIn";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

    /**
     * unifiedOrder
     */
    @Test
    public void unifiedOrder() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B2274696D655F657870697265223A223230313730353038313931303138222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C227265715F6970223A223132332E31322E31322E313233222C227465726D696E616C5F636F6465223A2231323334353637383931222C227375625F6D6572635F6964223A223830313534363336323734363838303032222C2263757272656E6379223A22313536222C226D6572635F6F726465725F6E6F223A223132333435363738393031323334353637383930222C227265715F74696D65223A223230313730353038313831303138227D");
        otherAmt.put("mac", "4331313043413538");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = localServerUrl + "/" + appId + "/posPay/unifiedOrder";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }


    /**
     * comsume
     */
    @Test
    public void comsume() throws IOException, NoSuchAlgorithmException, InterruptedException {



        Map<String, String> otherAmt = new HashMap<>();
        otherAmt.put("body", "7B2263636F6E5F6D6F64223A22303231222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C22636172645F6E6F223A2236323237303032343333333430333537323035222C2270696E5F64617461223A2230323244323531463144304444433934222C227465726D696E616C5F636F6465223A2231323334353637383931222C2274726164655F616D74223A22303030303030303030303031222C2274726164655F6E6F223A22313234323031373035303831383130343230303030303132313337303838222C22747261636B325F6E6F223A2236323237303032343333333430333537323035443432303335323031303631303230303030222C227265715F74696D65223A223230313730353131313730343533227D");
        otherAmt.put("mac", "3141344538384141");

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
        otherAmt.put("body", "7B2263636F6E5F6D6F64223A22303732222C2273616C74223A22313233343536222C226D6572635F6964223A2238303131303536383131323534353938393833363836222C22636172645F6E6F223A2236323132323630323030313334393333303634222C227465726D696E616C5F636F6465223A2231323334353637383931222C2274726164655F616D74223A22303030303030303030303031222C2274726164655F6E6F223A22313234323031373035303831383130343230303030303132313337303838222C22747261636B325F6E6F223A2236323132323630323030313334393333303634443237303432303138383539393931393036222C227265715F74696D65223A223230313730353039313335333235227D");
        otherAmt.put("mac", "4332304336303436");

        String body = new Gson().toJson(otherAmt);

        log.info("body:\n" + body);

        String appId = "1234567891";

        String token = "123456";

        String url = devServerUrl + "/" + appId + "/posPay/queryOrderInfo";

        log.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }

    @Test
    public void testCallBack() {
        String body = "7B2274726164655F737473223A2253554343455353222C22736574746C655F64617465223A223230313730353131222C2274726164655F616D74223A22303030303030303030303031222C2274726164655F6E6F223A22313234323031373035303831383130343230303030303132313337303838222C2263757272656E6379223A22313536222C226D6572635F6F726465725F6E6F223A223132333435363738393031323334353637383930227D";
        log.info(ConvHelper.HEX2STR(body));
    }


}
