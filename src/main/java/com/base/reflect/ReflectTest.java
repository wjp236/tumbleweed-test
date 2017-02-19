package com.base.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述:
 /**测试正常调用和反射调用下的执行效率问题。
 * 测试范围：构造函数，get，set方法；
 * 测试类：TestBean;
 * 测试过程：分别执行1,00,000,000次，统计时间；
 * 测试环境：JDK1.8，Intel i7,八核四线程，2.53GHz，内存16GB
 *
 * @author: mylover
 * @Time: 18/02/2017.
 */
public class ReflectTest {

    public static void main(String[]args){
        //普通调用
        TestBeanRun normal=new TestBeanRun();
        TestBeanCount.testBean(normal);
        //
        TestBeanRun norRef = new TestBeanRun() {
            @Override
            public void testConstruct() {
                Class<?> clazz;
                try {
//					clazz = TestBean.class;
                    clazz=Class.forName("com.base.reflect.TestBean");
                    super.tb = (TestBean) clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void testGet() {
                try {
                    Method m = TestBean.class.getMethod("getWord");
                    s = (String) m.invoke(tb);
                } catch (NoSuchMethodException | SecurityException
                        | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e) {
                }

            }

            @Override
            public void testSet() {
                try {
                    Method m = TestBean.class.getMethod("setWord",String.class);
                    m.invoke(tb, "testSet");
                } catch (NoSuchMethodException | SecurityException
                        | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e) {
                }
            }
        };
        TestBeanCount.testBean(norRef);
        TestBeanCount.testBean(new TestBeanRun(){
            Class<?>clazz=TestBean.class;
            Method mget;
            Method mset;
            {
                try {
                    mget=TestBean.class.getMethod("getWord");
                    mset=TestBean.class.getMethod("setWord",String.class);
                } catch (NoSuchMethodException | SecurityException  | IllegalArgumentException e) {
                }
            }
            @Override
            public void testConstruct() {
                try {
                    super.tb=(TestBean) clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void testGet() {
                try {
                    s=(String)mget.invoke(tb,null);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }

            }
            @Override
            public void testSet() {
                try {
                    mset.invoke(tb,"testSet");
                } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
            }
        });
    }


}