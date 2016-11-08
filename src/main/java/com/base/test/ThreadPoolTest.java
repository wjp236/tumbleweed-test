package com.base.test;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mylover on 1/28/16.
 */
public class ThreadPoolTest {

    private static int produceTaskSleepTime = 2;
    private static int produceTaskMaxNumber = 10;

    @org.junit.Test
    public void executorServiceTest() {



    }

    @org.junit.Test
    public void threadPoolTest() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 1; i <= produceTaskMaxNumber; i++)
        {
            try
            {
                // 产生一个任务，并将其加入到线程池
                String task = "task@ " + i;
                System.out.println("put " + task);
                threadPool.execute(new ThreadPoolTask(task));

                // 便于观察，等待一段时间
                Thread.sleep(produceTaskSleepTime);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    private static int queueDeep = 4;

    @org.junit.Test
    public void mainTest()
    {
        this.createThreadPool();
    }

    @org.junit.Test
    public void createThreadPool()
    {
        /*
         * 创建线程池，最小线程数为2，最大线程数为4，线程池维护线程的空闲时间为3秒，
         * 使用队列深度为4的有界队列，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，
         * 然后重试执行程序（如果再次失败，则重复此过程），里面已经根据队列深度对任务加载进行了控制。
         */
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueDeep),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        // 向线程池中添加 10 个任务
        for (int i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            while (getQueueSize(tpe.getQueue()) >= queueDeep)
            {
                System.out.println("队列已满，等3秒再添加任务");
                try
                {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            TaskThreadPool ttp = new TaskThreadPool(i);
            System.out.println("put i:" + i);
            tpe.execute(ttp);
        }

        tpe.shutdown();
    }

    private synchronized int getQueueSize(Queue queue)
    {
        return queue.size();
    }

    class TaskThreadPool implements Runnable
    {
        private int index;

        public TaskThreadPool(int index)
        {
            this.index = index;
        }

        public void run()
        {
            System.out.println(Thread.currentThread() + " index:" + index);
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}

class ThreadPoolTask implements Runnable, Serializable {
    private static final long serialVersionUID = 0;
    private static int consumeTaskSleepTime = 2000;
    // 保存任务所需要的数据
    private Object threadPoolTaskData;

    ThreadPoolTask(Object tasks) {
        this.threadPoolTaskData = tasks;
    }

    public void run() {
        // 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
        System.out.println(Thread.currentThread().getName());
        System.out.println("start .." + threadPoolTaskData);

        try {
            // //便于观察，等待一段时间
            Thread.sleep(consumeTaskSleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPoolTaskData = null;
    }

    public Object getTask() {
        return this.threadPoolTaskData;
    }
}
