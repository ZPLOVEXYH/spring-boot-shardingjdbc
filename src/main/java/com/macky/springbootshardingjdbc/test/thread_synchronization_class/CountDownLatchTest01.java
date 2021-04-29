package com.macky.springbootshardingjdbc.test.thread_synchronization_class;

// 倒计时器

import java.util.concurrent.CountDownLatch;

/**
 * 使用场景：火箭发射
 */
public class CountDownLatchTest01 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("科学家：" + temp + "已完成发射前的准备任务！");

                countDownLatch.countDown();// 子线程执行完毕了之后-1
            }, String.valueOf(i)).start();
        }

        try {
            // 主线程等待子线程执行完在执行后面的代码
            countDownLatch.await();// 直到子线程数量减到0之后才能唤醒主线程往下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("点火！发射！！！");
    }
}
