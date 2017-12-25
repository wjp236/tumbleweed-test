package com.tumbleweed.test.base.xzl;


import java.util.*;

/**
 * 描述: 测试集合
 *
 * @author: mylover
 * @Time: 23/12/2017.
 */
public class TestColl {

    @org.junit.Test
    public void test1() {

        Collection collection = new LinkedHashSet();

        Collection<Integer> list = new ArrayList<>();
        Collection list1 = new LinkedList();

        Collection set = new HashSet();
        Collection set1 = new TreeSet();


        list.add(1);
        list.add(2);


        list1.add("1");
        list1.add("1");
        list1.add("1");
        list1.add("1");

        set1.add("1");
        set1.add("2");
        set1.add("1");

        for (Integer temp : list) {
            System.out.println(temp);
        }

        for (Object temp : list1) {
            System.out.println(temp);
        }

        for (Object object : set1) {
            System.out.println(object);
        }
    }


    @org.junit.Test
    public void test2() {


        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new Hashtable<>();
        Map<String, String> map2 = new LinkedHashMap<>();

        map.put("test", "test");
        map.put("test", "test1");
        map.put("test", "test2");
        map.put("test", "test3");

        map1.put("test1", "test");

        map2.put("test2", "test");


        Set<String> keys = map1.keySet();

        System.out.println(keys.size());

        for (String temp : keys) {
            System.out.println(map1.get(temp));
        }

    }

}
