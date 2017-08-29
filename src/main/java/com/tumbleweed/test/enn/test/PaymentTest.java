package com.tumbleweed.test.enn.test;

import com.tumbleweed.test.base.idworder.IPUtils;
import com.tumbleweed.test.base.idworder.IdWorker;
import com.tumbleweed.test.enn.model.MerchantBaseRet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mylover on 6/3/16.
 */
public class PaymentTest {

    public Logger log = LogManager.getLogger(PaymentTest.class);
    private static long datacenterId = Long.valueOf(IPUtils.getLocalIp().substring(IPUtils.getLocalIp().length() - 1));

    @Test
    public void mchtBaseRetProc() {
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map.put("pay_type", "pay_type");
        map.put("trade_no", "trade_no");
        log.info(this.mchtBaseRetProc("test", gson.toJson(map)));
    }

    public String mchtBaseRetProc(String merchantId,
                                  String result) {

        MerchantBaseRet<String> baseRet = new MerchantBaseRet<>();
        baseRet.setRetCode("000000");
        baseRet.setRetMsg("000000");
//		baseRet.setMerchantId(merchantId);
//		baseRet.setSalt(RandomUtil.generateString(32));
        baseRet.setResult(result);
//
//		if (merchantId == null) { // 如果商户id为null，则无需签名
//			return new Gson().toJson(baseRet);
//		}

//		String key = getMerchantKey(merchantId);

//		Map<String, String> tmp = signData(GsonHelper.convertBean(baseRet), key);

        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();

        return gb.create().toJson(baseRet);
    }

    @Test
    public void test() {
        IdWorker idWorker = new IdWorker(9, datacenterId);
        System.out.println(idWorker.nextId());
    }

}
