package com.tumbleweed.test.base.reflect;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 18/02/2017.
 */
public class TestBeanRun implements Runnable{
    public static final int CONSTRUCT=1;
    public static final int GET=2;
    public static final int SET=3;
    private int testMethod;
    protected String s;
    protected TestBean tb;
    private final static TestBean TB = new TestBean();
    public void setTestMethod(int testMethod){
        this.testMethod=testMethod;
    }
    @Override
    public void run() {
        switch(this.testMethod){
            case CONSTRUCT:
                testConstruct();
                break;
            case GET:
                testGet();
                break;
            case SET:
                testSet();
                break;
            default:

        }
    }
    public void testConstruct(){
        tb=new TestBean();
    }
    public void testGet(){
        s=TB.getWord();
    }
    public void testSet(){
        TB.setWord("ANOTHER");
    }

}