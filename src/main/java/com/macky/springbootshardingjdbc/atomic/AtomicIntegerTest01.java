package com.macky.springbootshardingjdbc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试atomic的原子性
 */
public class AtomicIntegerTest01 {

    // 原子操作
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            final int j = i;
            final AtomicInteger atomicInt = atomicInteger;
            new Thread(() -> {
                for (int i1 = 1; i1 <= 100; i1++) {
                    synchronized (atomicInt) {
                        int andIncrement = atomicInt.getAndIncrement(); // 2s
                        System.out.println(Thread.currentThread().getName() + "====>" + andIncrement);
                    }
                }
            }, "当前线程" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("获取得到的总数为：" + atomicInteger.get());
    }
}
