package com.tumbleweed.test.base.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SystemTest {

    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        method(a, b);//打印a:100,b:200

        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }

    private static void method(int a, int b) {

        Map<Thread, StackTraceElement[]> elements = Thread.currentThread().getAllStackTraces();

        Set<Map.Entry<Thread, StackTraceElement[]>> set = elements.entrySet();
        for (Iterator<Map.Entry<Thread, StackTraceElement[]>> it = set.iterator(); it.hasNext();) {
            Map.Entry<Thread, StackTraceElement[]> entry = it.next();
            System.out.println(entry.getKey());
            for (StackTraceElement stackTraceElement : entry.getValue()) {
                System.out.print(stackTraceElement.getClass() + "\t");
                System.out.print(stackTraceElement.getClassName() + "\t");
                System.out.print(stackTraceElement.getFileName() + "\t");
                System.out.print(stackTraceElement.getLineNumber() + "\t");
                System.out.print(stackTraceElement.getMethodName() + "\t\n");
            }
        }
    }

}
