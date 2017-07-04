package com.base.idwork;

import com.base.idworder.IdWorker;
import com.base.idwork.IdService;
import com.base.idwork.impl.UUIDServiceImpl;
import com.base.thread.BitUtil;
import com.relops.snowflake.Snowflake;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 描述: 测试
 *
 * @author: mylover
 * @Time: 26/06/2017.
 */
public class TestIdWorker {

    private static int thread_num = 200;
    private static int client_num = 460;

    @Test
    public void test1() {

        IdService uuidService = new UUIDServiceImpl();
        System.out.println("insert into bit_id values ('" + uuidService.orderId() + "');");

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        final Semaphore semp = new Semaphore(thread_num);
//        for (int index = 0; index < client_num; index++) {
//            final int no = index;
//            Runnable run = () -> {
//                try {
//                    semp.acquire();
//                    System.out.println("insert into bit_id values ('" + uuidService.orderId() + "');");
//                    semp.release();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            };
//            executorService.execute(run);
//        }
//        executorService.shutdown();
    }

    @Test
    public void test2() {
        IdWorker idWorker = new IdWorker(1, 12);
        System.out.println(idWorker.nextId());
    }

    @Test
    public void test3() {
        Snowflake snowflake = new Snowflake(1);
        System.out.println(snowflake.next());
    }


}
