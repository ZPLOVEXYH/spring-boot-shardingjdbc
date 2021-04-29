package com.macky.springbootshardingjdbc.volatile_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用AtomicInteger来保证原子性操作
 */
public class VolatileTest03 {

    private static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        test1();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(num);
    }

    private static void test1() {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                    System.out.println(num.get());
                }
            }).start();
        }
    }

    private static void add() {
        num.getAndIncrement();
    }
}
