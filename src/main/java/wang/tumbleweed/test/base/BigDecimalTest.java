package wang.tumbleweed.test.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.Test;
import wang.tumbleweed.util.StringUtil;

import java.math.BigDecimal;

/**
 * Created by mylover on 12/24/15.
 */
public class BigDecimalTest {

    public Logger log = LogManager.getLogger(BigDecimalTest.class);

    @Test
    public void test1() {
        ThreadContext.push(StringUtil.getUUID4MD5());
        BigDecimal b1 = new BigDecimal(500);
        BigDecimal b2 = new BigDecimal(501);

        log.info(b1);
        log.info(b2);

        if (b2.compareTo(b1) < 0) {
            log.info("true");
        }

        log.info("----- {}", b2.compareTo(b1));
    }

}
