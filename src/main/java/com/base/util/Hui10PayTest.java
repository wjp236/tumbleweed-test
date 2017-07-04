package com.base.util;

import com.base.common.Base64;
import com.base.common.HttpPostUtil;
import com.base.common.MD5;
import com.enn.util.DateUtils;
import com.enn.util.MD5SignAndValidate;
import com.google.gson.Gson;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mylover on 4/9/16.
 */
public class Hui10PayTest {

    private final static Logger logger = LoggerFactory.getLogger(Hui10PayTest.class);

    private static final String localServerUrl = "http://localhost:8080/webCash";
    private static final String devServerUrl = "http://172.16.254.222:9022/webCash";
    private static final String testServerUrl = "http://172.16.252.218:9022/webCash";

    @Test
	public void testUnifiedOrder() throws Exception {

        HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "123456");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_desc", "订单测试");
        tradeParamMap.put("trade_detail", "订单测试");
        tradeParamMap.put("merc_order_no", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_amt", "0.01");
        tradeParamMap.put("currency", "CNY");
        tradeParamMap.put("req_ip", "123.12.12.123");
        tradeParamMap.put("sub_merc_id", "801300440848670728");
//        tradeParamMap.put("time_expire", "20170510112020");
        tradeParamMap.put("server_notify_url", "https://devpay.hui10.com:8443/inner/A99999/make/metCallBack");
//        tradeParamMap.put("server_notify_url", "http://testpay.hui10.com:8922/inner/A99999/make/metCallBack");
//      tradeParamMap.put("server_notify_url", "http://172.16.254.2:8080//inner/A99999/make/metCallBack");

        tradeParamMap.put("client_notify_url", "https://devpay.hui10.com:8443/inner/A99999/make/metCallBack"); // 客户端通知地址
        tradeParamMap.put("buyer_user_id","ohe9gxHOHTUbEPI_VbPqGalJmkpM");
        
        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "123456");

        String body = new Gson().toJson(tradeParamMapWithSign);

        logger.info("body:\n" + body);

      String appId = "A99999";
      String token = "123456";
//      String url = devServerUrl + "/" + appId + "/pay/unifiedOrder";
        
//        String appId = "A88888";
//        String token = "123456";
//        
        String url = testServerUrl + "/" + appId + "/pay/unifiedOrder";
        
        logger.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);

    }
    
    @Test
    public void testCharge() throws Exception {
    
    	HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "123456");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_no", "124201706210956590000013415040");
        tradeParamMap.put("charge_type", "2");
        tradeParamMap.put("tradeChannel", "WXMP");
        
        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "123456");

        String body = new Gson().toJson(tradeParamMapWithSign);

        logger.info("body:\n" + body);

        
        
//        String appId = "A00002";
//        String token = "3f37fce3c82e4eacbbcfdd5d2cd55487";
//        String url = devServerUrl + "/" + appId + "/pay/charge";
        String appId = "A99999";
        String token = "123456";
        String url = testServerUrl + "/" + appId + "/pay/charge";
        
        logger.info(url);

        String res = HttpPostUtil.sendJSON(appId, token, url, body);
        
        Map<String, String> map =  GsonHelper.convertMapFromJson(res);
        String result = map.get("result");
        Map<String, String> aosMap = GsonHelper.convertMapFromJson(result);
        logger.info("arouseSDKParam----->{}",aosMap.get("arouseSDKParam"));
    }
    
    @Test
    public void testClose() throws Exception {
    	
    	HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "123456");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_no", "124201706151626120000010269312");
        
        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "123456");
        
        String body = new Gson().toJson(tradeParamMapWithSign);

        logger.info("body:\n" + body);

        String appId = "A99999";

        String token = "123456";

//        String url = devServerUrl + "/" + appId + "/pay/closeOrder";
        String url = testServerUrl + "/" + appId + "/pay/closeOrder";
        
        logger.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);
    	
    }
    
    @Test
    public void testQuery() throws Exception {
    	
    	HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "123456");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_no", "124201706151626120000010269312");
        
        Map<String, String> tradeParamMapWithSign = MD5SignAndValidate.signData(tradeParamMap, "123456");
        
        String body = new Gson().toJson(tradeParamMapWithSign);

        logger.info("body:\n" + body);

        String appId = "A99999";

        String token = "123456";

//        String url = devServerUrl + "/" + appId + "/pay/queryOrderInfo";
        String url = testServerUrl + "/" + appId + "/pay/queryOrderInfo";
        
        logger.info(url);

        HttpPostUtil.sendJSON(appId, token, url, body);
    	
    }
    
    @Test
    public void makeSig() throws Exception {
        String appId = "A99999";
        String token = "123456";
        String time = "20170411121212";

        logger.info("appId:{},token:{},time:{}",appId, token, time);


        String authorization = appId + ":" + time;
        logger.info("\nbase authorization:{}", authorization);

        String sig = appId + token + time;
        logger.info("\nbase sig:{}", sig);

        logger.info("\n加密后 authorization:{}", Base64.encodeToString(authorization));
        logger.info("\n加密后 sig:{}", MD5.md5(sig));
    }
    
    @Test
    public void testSign() throws Exception {
    	
    	HashMap<String, String> tradeParamMap = new HashMap<String, String>();
        tradeParamMap.put("merc_id", "8011056811254598983686");
        tradeParamMap.put("salt", "123456");
        tradeParamMap.put("req_time", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_desc", "订单测试");
        tradeParamMap.put("trade_detail", "订单测试");
        tradeParamMap.put("merc_order_no", DateUtils.getCurrentDateTime());
        tradeParamMap.put("trade_amt", "0.01");
        tradeParamMap.put("currency", "CNY");
        tradeParamMap.put("req_ip", "123.12.12.123");
        tradeParamMap.put("sub_merc_id", "80126913246453768");
        tradeParamMap.put("server_notify_url", "http://172.16.0.184:8080/inner/make/metCallBack");
        
        String appId = "A99999";
        
        String token = "123456";
        
        String url = "http://172.16.254.222:9022" + "/inner/" + appId +"/make/signature";
        
        String body = new Gson().toJson(tradeParamMap);
    
        HttpPostUtil.sendJSON(appId, token, url, body);
        
    }
    
    @Test
    public void testRate() {
    	
    	String rate = "0.380";
    	BigDecimal trueRate = new BigDecimal(rate).divide(new BigDecimal("100"));
    	
    	logger.info("trueRate={}",trueRate);
    	
    	BigDecimal tradeAmt = new BigDecimal("301");
//    	BigDecimal fee = tradeAmt.multiply(trueRate);
    	BigDecimal fee = new BigDecimal("1.905");
    	BigDecimal max = new BigDecimal("10");
    	BigDecimal min = new BigDecimal("1");
    	
    	if(fee.compareTo(min) == -1) {
    		fee = min;
    	}
    	
    	if (fee.compareTo(max) == 1) {
    		fee = max;
    	}
    	
    	// 四舍五入取两位小数
    	BigDecimal trueFee = fee.setScale(2,RoundingMode.HALF_UP);
    	double dtrueFee = trueFee.doubleValue();
    	logger.info("fee={}",fee);
    	logger.info("trueFee={}",trueFee);

    	
    }

}
