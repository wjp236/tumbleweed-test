package com.tumbleweed.test.base.hashmap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 描述:自我实现hashMap
 *
 * @author: mylover
 * @Time: 09/02/2017.
 */
public class TestHashMap {


    public static final Logger log = LogManager.getLogger(TestHashMap.class);


    @Test
    public void testHashMap() {

        WangMap<String, String> wangMap = new WangHashMap<>();
        for (int i = 0; i < 1000; i++) {
            wangMap.put("k" + i, "v" + i);
        }

        for (int i = 0; i < 1000; i++) {
            log.info("key:" + "k" + i + ", value:" + wangMap.get("k" + i));
        }

        log.info("size:" + wangMap.size());

    }


    @Test
    public void test() {
        String t1 = "123457891";

        System.out.println(t1.hashCode());
    }



    @Test
    public void test1() {
        String value = "我是中国人";
        char[] chars = value.toCharArray();

        CharColumn<String>[] columns = new CharColumn[0];

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            System.out.println("---------:" + c);
        }
    }

}
