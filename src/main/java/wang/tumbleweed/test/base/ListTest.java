package wang.tumbleweed.test.base;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mylover on 10/30/15.
 */
public class ListTest {

    public Logger log = LogManager.getLogger(ListTest.class);

    @Test
    public void makeSig() throws NoSuchAlgorithmException {
        List<String> lists = new ArrayList<String>();
        lists.add("1");
        lists.add("2");

        for (String temp : lists) {
        }
    }

    @Test
    public void testList() {
        List<String> list = null;

        String str = StringUtils.join(list,"#");

        log.info(str);
    }

    @Test
    public void test1() {
        PriorityQueue<String> strLists = new PriorityQueue<String>();

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();


    }
}
