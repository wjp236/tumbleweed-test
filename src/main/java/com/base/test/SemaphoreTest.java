package com.base.test;

import com.google.common.util.concurrent.RateLimiter;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by mylover on 16/11/2016.
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    //速率是每秒两个许可
    final RateLimiter rateLimiter = RateLimiter.create(2.0);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                    try {
                        s.acquire();
                        System.out.println("save data");
                        s.release();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            );
        }
        threadPool.shutdown();
/*
        int availablePermits() ：返回此信号量中当前可用的许可证数。
        int getQueueLength()：返回正在等待获取许可证的线程数。
        boolean hasQueuedThreads() ：是否有线程正在等待获取许可证。
        void reducePermits(int reduction) ：减少reduction个许可证。是个protected方法。
        Collection getQueuedThreads() ：返回所有等待获取许可证的线程集合。是个protected方法。*/
    }

    //令牌桶算法
    void submitTasks(List<Runnable> tasks, Executor executor) {
        for (Runnable task : tasks) {
            rateLimiter.acquire(); // 也许需要等待
            executor.execute(task);
        }
    }



}
