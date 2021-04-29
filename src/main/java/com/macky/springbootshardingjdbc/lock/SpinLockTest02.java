package com.macky.springbootshardingjdbc.lock;

import java.util.concurrent.TimeUnit;

public class SpinLockTest02 {

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            // 加锁
            spinLockDemo.myLock();
            try {
                // a线程休眠2秒钟，让b线程执行
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 解锁
                spinLockDemo.myUnLock();
            }
        }, "a").start();

        new Thread(() -> {
            // 加锁
            spinLockDemo.myLock();
            try {
                // a线程休眠2秒钟，让b线程执行
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 解锁
                spinLockDemo.myUnLock();
            }
        }, "b").start();
    }
}
