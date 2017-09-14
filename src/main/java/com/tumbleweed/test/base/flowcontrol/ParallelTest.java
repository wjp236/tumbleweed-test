package com.tumbleweed.test.base.flowcontrol;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 描述:并发控制
 *
 * @author: mylover
 * @Time: 30/08/2017.
 */
public class ParallelTest {

    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        executorService.submit(() -> handler());
    }

    private static void handler() {
        boolean acquire = acquire();
        try {
            if (acquire) {
                System.out.println("业务执行中");
            } else {
                System.out.println("我被拒绝了");
            }
        } finally {
            release();
        }
    }

    static final Semaphore semaphore = new Semaphore(10);

    /**
     * 获取
     * @return
     */
    public static boolean acquire() {
        return semaphore.tryAcquire();
    }

    /**
     * 释放
     * @return
     */
    public static void release() {
        semaphore.release();
    }




}
