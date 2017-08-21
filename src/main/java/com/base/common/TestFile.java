package com.base.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 13/07/2017.
 */
public class TestFile {


    @Test
    public void test() {
        String t1 = "123456";

        Map<String, File> map = new HashMap<>();

        File f1 = new File();
        f1.setI(1);
        f1.setTime("20170101");
        map.put(t1, f1);

        String t2 = "123457";
        String t3 = "123458";
        String t4 = "123456";
        List<String> list = new ArrayList<>();
        list.add(t2);
        list.add(t3);
        list.add(t4);

        for (String s : list) {
            if (map.get(s) == null) {
                File f2 = new File();
                f2.setI(1);
                f2.setTime("20170102");

                map.put(s, f2);
            } else {
                File f3 = map.get(s);
                f3.setI(f3.getI() + 1);
            }
        }

        for(Map.Entry<String, File> entry:map.entrySet()){
            System.out.println("key------"+entry.getKey());
            System.out.println("value--------"+entry.getValue().getI() + "," + entry.getValue().getTime());
        }

    }



}
