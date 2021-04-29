package com.macky.springbootshardingjdbc.test.thread_synchronization_class;

// 信号量

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 使用场景，10个人排队使用3个厕所
 */
public class SemaphoreTest01 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();// 获取许可证
                    System.out.println("路人" + temp + "进入了厕所！");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("路人" + temp + "离开了厕所！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放许可证
                }
            }, String.valueOf(i)).start();
        }
    }
}
