package com.base.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述: 线程类
 *
 * @author: mylover
 * @Time: 26/06/2017.
 */
public class BitUtil {

    public static long timeTasks( int nThreads, int singleNum, final Runnable task) {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        ThreadFactory tf = Executors.defaultThreadFactory();
        final int singleExeNum = singleNum == 0 ? 1 : singleNum;
//        final AtomicLong sum = new AtomicLong();
//        final AtomicLong min = new AtomicLong(10000);
//        final AtomicLong max = new AtomicLong(0);
        for (int i = 0; i < nThreads; i++) {
            tf.newThread(() -> {
                long start = System.nanoTime();
                try {
                    startGate.await();
                    for (int j = 0; j < singleExeNum; j++) {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    long end = System.nanoTime();
//                    long at = ((end - start) /1000) / 1000;
//                    sum.addAndGet(at);
//                    if (min.get() > at) {
//                        min.getAndSet(at);
//                    }
//                    if (max.get() < at) {
//                        max.getAndSet(at);
//                    }
                }

            });
            try {
                endGate.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
