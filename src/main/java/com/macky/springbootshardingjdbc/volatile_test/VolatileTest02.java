package com.macky.springbootshardingjdbc.volatile_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile java关键词的作用：
 * 1、保证可见性；
 * 2、不保证原子性；
 * 3、禁止指令重排；
 */
public class VolatileTest02 {

    private volatile static int num = 0;

    public static void main(String[] args) {
        test1();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(num);
    }

    // 测试volatile不保证原子性
    private static void test1() {
        VolatileTest02 volatileTest02 = new VolatileTest02();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    volatileTest02.add();
                }
            }).start();
        }

    }

    private Lock lock = new ReentrantLock();

    /**
     * 使用lock锁来保证线程原子性操作
     */
    private void add() {
        lock.lock();
        try {
            num++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * num累加1
     */
//    private synchronized static void add() {
//        num++;
//    }
}
