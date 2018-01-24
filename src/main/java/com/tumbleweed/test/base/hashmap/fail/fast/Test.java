package com.tumbleweed.test.base.hashmap.fail.fast;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 测试
 *
 * @author: mylover
 * @Time: 16/01/2018.
 */
public class Test {

    @org.junit.Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        TestMap a = new TestMap(map);
        a.start();

        TestMap2 b = new TestMap2(map);
        b.start();
    }

}

class TestMap extends Thread {

    private Map<String, Object> map;

    public TestMap(Map<String, Object> map) {
        super();
        this.map = map;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100000000; i++) {
            map.put("test" + i, i);
//            System.out.println("test" + i +"--------" + map.get("test" + i));
        }

    }
}


class TestMap2 extends Thread {

    private Map<String, Object> map;

    public TestMap2(Map<String, Object> map) {
        super();
        this.map = map;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100000000; i++) {
            System.out.println("test" + i +"--------" + map.get("test" + i));
        }

    }
}