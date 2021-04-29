package com.macky.springbootshardingjdbc.test.thread_synchronization_class;

// 循环障碍,加数器

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用场景，集齐7颗龙珠可以召唤神龙
 */
public class CyclicBarrierTest01 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("恭喜你，成功召唤神龙！"));

        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println("成功集齐第" + temp + "颗龙珠！");
                if (5 == temp) {
                    // 当执行到第5次的时候，中断当前线程；
                    Thread.currentThread().interrupt();
                }

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
