package com.tumbleweed.test.base.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 描述:信号量
 *
 * @author: mylover
 * @Time: 22/01/2018.
 */
public class SemaphoreTest {
    private static final int NUMBER = 5;    //限制资源访问数
    private static final Semaphore avialable = new Semaphore(NUMBER,true);
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Runnable r = () -> {
            try {
                avialable.acquire();    //此方法阻塞
                Thread.sleep(10*1000);
                System.out.println(getNow()+"--"+Thread.currentThread().getName()+"--执行完毕");
                avialable.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println(avialable.availablePermits());
        for(int i=0;i<10;i++){
            pool.execute(r);
        }
        System.out.println(avialable.availablePermits());
        pool.shutdown();
    }

    public static String getNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(new Date());
    }
}
