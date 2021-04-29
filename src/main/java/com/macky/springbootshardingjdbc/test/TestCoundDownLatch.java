package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.CountDownLatch;

/**
 * 场景2 让单个线程等待：多个线程(任务)完成后，进行汇总合并
 */
public class TestCoundDownLatch {

    public static void main(String[] args) {
        // 放学了，还有6位同学还没有放学，校门不能关闭
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "放学了，出了校门！");
                countDownLatch.countDown();// 倒计时数量-1
            }, String.valueOf(i)).start();
        }

        try {
            // 等待计数器归零之后方可执行下一步的操作
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Close Door");
    }
}
