package com.macky.springbootshardingjdbc.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池：3大方法、7种核心参数、4种拒绝策略
 */
public class ThreadPoolTest01 {

    public static void main(String[] args) {
        test1();
    }

    /**
     * int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * ThreadFactory threadFactory,
     * RejectedExecutionHandler handler
     *
     * @param
     */
    public static void test1() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        // 访问的线程超过了：最大值（5）+队列的容量值（2）=8
        for (int i = 1; i <= 8; i++) {
            final int temp = i;
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "===>" + temp);
            });
        }

        threadPool.shutdown();
    }
}
