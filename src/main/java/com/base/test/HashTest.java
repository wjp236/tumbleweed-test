package com.base.test;

import com.base.model.BaseRet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 08/12/2016.
 */
public class HashTest {

    public Logger logger = LogManager.getLogger(HashTest.class);

    @org.junit.Test
    public void test() {
        BaseRet baseRet = new BaseRet();
        logger.info(baseRet.hashCode());
    }

}
