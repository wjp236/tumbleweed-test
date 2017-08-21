package com.hui10.test;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
* IdWorker Tester.
* 
* @author <Authors name>
* @since <pre>七月 6, 2017</pre>
* @version 1.0 
*/ 
public class IdWorkerTest {

    IdWorker idWorker = new IdWorker(13,212);

    @Before
    public void before() throws Exception {
    }
    
    @After
    public void after() throws Exception {
    }
    
        /**
    * 
    * Method: nextId() 
    * 
    */
    @Test public void nextId() throws Exception {
        String si = Long.toBinaryString(1L << 32);
        System.out.println(1L<<32);
        System.out.println(si);
        //4294967296
        //1 00000 0000000000 00000000000000000
    }
    
        /**
    * 
    * Method: decodeId(long encodeId) 
    * 
    */
    @Test
    public void decodeId() throws Exception {
        long id = idWorker.nextId();
        System.out.println(id);
        System.out.println(Long.toBinaryString(id));
        Map<String,String> map = idWorker.decodeId(id);

        System.out.printf("ID的秒数：%s\n",map.get("seconds"));
        System.out.printf("ID的生产时间：%s\n",map.get("dateTime"));
        System.out.printf("ID的业务类型：%s\n",map.get("buzId"));
        System.out.printf("ID的节点：%s\n",map.get("nodeId"));
        System.out.printf("ID的序列：%s\n",map.get("sequence"));
        String sldt = map.get("dateTime");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(sldt,formatter);
        System.out.println(formatter.format(ldt));
        System.out.println(ldt);
    }
}
