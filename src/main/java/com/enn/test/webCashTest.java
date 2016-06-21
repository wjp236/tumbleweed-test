package com.enn.test;

import com.base.common.EncryptUtil;
import com.enn.common.HttpPostUtil;
import com.enn.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mylover on 4/9/16.
 */
public class WebCashTest {

    public Logger log = LogManager.getLogger(WebCashTest.class);

    private static final String localServerUrl = "http://localhost:8080/webCash";
    private static final String serverUrl = "http://10.37.148.50:9022/webCash-core/webCash";

    @Test
    public void log4j2Buddhism() {
        StringBuffer sb = new StringBuffer("\n");
        sb.append("           *            \n");
        sb.append("        *      *        \n");
        sb.append("     *            *     \n");
        sb.append("   *    *      *    *   \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        sb.append("        *      *        \n");
        log.info(sb.toString());
    }

    @Test
    public void makePwd() {
        String pwd = EncryptUtil.md5(EncryptUtil.md5("123456") + "123456");
        log.info(pwd);
    }

    /**
     * 用户绑定签名
     */
    @Test
    public void binding() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("salt","123456");
        json.put("merc_id", "8011056811254598983686");
        json.put("phone", "18600200156");
        json.put("req_time", "20160428162626");
        json.put("thr_user_no", "18600200156");
        log.info(json);
        String body = json.toString();
        String url = serverUrl + "/xinyi/make/signature";
        HttpPostUtil.sendJSON(url, body);
    }


    /**
     * 唤起收银台签名
     */
    @Test
    public void makeSignature() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("salt","123456");
        json.put("merc_id", "8011056811254598983686");
        json.put("trade_no", "124201605171927340664010024576");
        json.put("user_token", "6a25c4f617c8489a8d6957e8a5d4dbe8");
        json.put("req_time","20160428105935");
        log.info(json);

        String body = json.toString();
        String url = localServerUrl + "/xinyi/make/signature";
        HttpPostUtil.sendJSON(url, body);
    }

    /**
     * test
     */
    @Test
    public void test0() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("id","中文乱码有问题吗");
        String body = json.toString();
        String url = "http://localhost:8080/webCash/xinyi/error/test";
        HttpPostUtil.sendJSON(url, body);
    }

    /**
     * toBeSeller
     */
    @Test
    public void toBeSeller() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_time", "20160426163043");
        json.put("user_token", "16f71ae9eae144d2a5c22a0922480257");
        String bodysignature = json.toString();
        String urlsignature = serverUrl + "/xinyi/make/signature";
        String body = HttpPostUtil.sendJSON(urlsignature, bodysignature);

        String url = serverUrl+"/custom/all/toBeSeller";
        HttpPostUtil.sendJSON(url, body);
    }


    /**
     * 统一下单
     */
    @Test
    public void unifiedOrder() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_ip", "127.0.0.1");
        json.put("receive_no", "16f71ae9eae144d2a5c22a0922480257");
        json.put("biz_type", "GOODS");
        json.put("trade_amt", "1.0");
        json.put("trade_desc", "篮球");
        json.put("req_time", "20160426163043");
        json.put("trade_mode", "GUARANTEEPAY");
        json.put("trade_detail", "秘制篮球");
        json.put("currency", "CNY");
        json.put("pay_type", "WAP");
        json.put("merc_order_no", "TEST001");
        json.put("attach", "userdata");
        String body = json.toString();
        log.info(body);
        String urlsignature = serverUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = localServerUrl + "/pay/all/unifiedOrder";
        HttpPostUtil.sendJSON(url, requestBody);
    }


    /**
     * 确认收货
     */
    @Test
    public void confirmReceipt() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_time", "20160426163043");
        json.put("trade_no", "124201605171927340664010024576");

        String body = json.toString();
        log.info(body);
        String urlsignature = serverUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlsignature, body);

        String url = serverUrl + "/pay/all/confirmReceipt";
        HttpPostUtil.sendJSON(url, requestBody);
    }



    /**
     * 调起收银台
     */
    @Test
    public void test2() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("id","123456");
        String body = json.toString();
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String url = "http://localhost:8080/webCash/pay/app/unifiedOrder";
        HttpPostUtil.sendJSON(url, body);
    }

    /**
     * 设置成功后端回调
     */
    @Test
    public void test3() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("thr_user_no","90090");
        json.put("result", "SUCCESS");
        json.put("thr_login_no","liuzw");
        String body = json.toString();
        log.info(body);
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String url = "http://10.37.149.92:8351/3rd/pay/notifyBandResult";
        HttpPostUtil.sendJSON(url, body);
    }

    /**
     * 信息查询
     */
    @Test
    public void test4() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("merc_id", "8011056811254598983686");
        json.put("salt", "123456789");
        json.put("req_time", "20160426163043");
        json.put("trade_no", "20160426163043");
        json.put("user_token", "833b657e7c2840ac90ccb3aecaec0354");

        String body = json.toString();
        String urlSignature = localServerUrl + "/xinyi/make/signature";
        String requestBody = HttpPostUtil.sendJSON(urlSignature, body);

        String url = localServerUrl+"/custom/app/info";
        HttpPostUtil.sendJSON(url, requestBody);
    }

    /**
     * 支付成功后端回调
     */
    @Test
    public void test5() throws IOException, NoSuchAlgorithmException {
        JSONObject json = new JSONObject();
        json.put("ret_code", "SUCCESS");
        json.put("ret_msg", "支付成功");
        json.put("merc_id","5a0de7219b274a53bd4c4431ee756517");
        json.put("salt", StringUtil.getUUID4MD5());
        json.put("signature", "xinyiPay");

        JSONObject resultJson = new JSONObject();
        resultJson.put("user_id", StringUtil.generateString(22));
        resultJson.put("trade_type", "WAP");
        resultJson.put("pay_cnl","QP");
        resultJson.put("trade_amt", "1000.00");
        resultJson.put("currency", "CNY");
        resultJson.put("trade_no", StringUtil.getUUID4MD5());
        resultJson.put("merc_order_no", "20016");
        resultJson.put("pay_date", "20160419");
        resultJson.put("pay_time", "15:30:00");
        resultJson.put("attach", "1180165209");
        resultJson.put("trade_sts", "SUCCESS");
        resultJson.put("account_date", "20160419");

        json.put("result", resultJson);
        String body = json.toString();
        HttpPostUtil HttpPostUtil = new HttpPostUtil();
        String url = "http://10.37.149.92:8351/3rd/pay/notifyPaymentResult";
        log.info(body);
        HttpPostUtil.sendJSON(url, body);
    }

}
