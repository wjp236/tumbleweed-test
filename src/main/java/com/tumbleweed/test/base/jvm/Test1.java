package com.tumbleweed.test.base.jvm;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 05/09/2017.
 */
public class Test1 {

    @Test
    public void test1() {
        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);
        //获取栈首元素后，元素不会出栈
        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);
        while(deque.size() > 0) {
            //获取栈首元素后，元素将会出栈
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }

}
