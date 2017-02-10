package com.enn.hashMap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 10/02/2017.
 */
public class TestHashMap {

    @Test
    public void testHashMap() {
        long t1 = System.currentTimeMillis();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            map.put("k" + i, "v" + i);
        }
        long t2 = System.currentTimeMillis();

        System.out.println("time:" + (t2 - t1));
    }

    @Test
    public void  testWangJPHashMap() {
        long t1 = System.currentTimeMillis();
        WangJPMap<String, String> map = new WangJPHashMap<>();
        for (int i = 0; i < 16; i++) {
            map.put("k" + i, "v" + i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("time:" + (t2 - t1));

//        for (int i = 0; i < 1000; i++) {
//            System.out.println("key:" + "k" + i + ", value:" + map.get("k" + i));
//        }


    }

}
