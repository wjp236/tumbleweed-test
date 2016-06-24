package com.base.test;

import com.base.common.TradeStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by mylover on 6/24/16.
 */
public class EnumTest {

    public Logger log = LogManager.getLogger(EnumTest.class);

    @org.junit.Test
    public void test1() {
        TradeStatus tradeStatus = TradeStatus.codeOf("1");

        log.info(tradeStatus.toString());
        log.info(tradeStatus.code());
        log.info(tradeStatus.getText());

        log.info(TradeStatus.WAITPAY.toString());
    }

}
