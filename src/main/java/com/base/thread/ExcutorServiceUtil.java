package com.base.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by mylover on 31/10/2016.
 */
public class ExcutorServiceUtil {

    protected static final Logger logger = LoggerFactory.getLogger(ExcutorServiceUtil.class);

    @Test
    public void test() throws InterruptedException {
        // 创建一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            System.out.println("创建线程" + i);
            Runnable run = () -> {
                System.out.println("启动线程");
                System.out.println("启动线程");
            };
            // 在未来某个时间执行给定的命令
            service.execute(run);
        }
        // 关闭启动线程
        service.shutdown();
        // 等待子线程结束，再继续执行下面的代码
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("all thread complete");
    }


    public void runTest(ExecutorService threadPool) {
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            threadPool.execute(() -> {
                for(int i1 = 1; i1 < 5; i1++) {
                    try {
                        Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第" + taskID + "次任务的第" + i1 + "次执行");
                }
            });
        }
        threadPool.shutdown();// 任务执行完毕，关闭线程池
    }

    @Test
    public void mainTest() {
        // 创建可以容纳3个线程的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        // 线程池的大小会根据执行的任务数动态分配
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        // 效果类似于Timer定时器
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

//        runTest(fixedThreadPool);
//        runTest(cachedThreadPool);
//      runTest(singleThreadPool);
//      runTest(scheduledThreadPool);
    }

}
