package com.base.reflect;

/**
 * 描述:  简易测试框架，统计运行时间
 *
 * @author: mylover
 * @Time: 18/02/2017.
 */
public class TestBeanCount {

    public static void testBean(TestBeanRun tbr){
        System.out.printf("%10s\t%5s\t%6s\n","method","time","avr" );
        long[]time=new long[3];
        String[] methods = new String[] { "construct", "get", "set" };
        for (int t = 10; t-- > 0;) {
            tbr.setTestMethod(TestBeanRun.CONSTRUCT);
            time[0] = getUsedTime(tbr);
            tbr.setTestMethod(TestBeanRun.GET);
            time[1] = getUsedTime(tbr);
            tbr.setTestMethod(TestBeanRun.SET);
            time[2] = getUsedTime(tbr);
            for (int k = 0; k < 3; ++k) {
                System.out.printf("%10s\t%5d\t%6.3f\n", methods[k], time[k],
                        time[k] * 0.001);
            }
        }
    }
    public static final long DEFAULT_RUN_TIME=100000000L;
    public static long getUsedTime(Runnable run,long runTime){
        long start=System.currentTimeMillis();
        while(runTime-->0){
            run.run();
        }
        return System.currentTimeMillis()-start;
    }
    public static long getUsedTime(Runnable run){
        return getUsedTime(run,DEFAULT_RUN_TIME);
    }

}
