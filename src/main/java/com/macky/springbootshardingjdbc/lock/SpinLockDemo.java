package com.macky.springbootshardingjdbc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁DEMO
 */
public class SpinLockDemo {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 自定义自旋锁加锁
     */
    public void myLock() {
        System.out.println(Thread.currentThread().getName() + "====>myLock");

        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {

        }
    }

    /**
     * 自定义自旋锁解锁
     */
    public void myUnLock() {
        System.out.println(Thread.currentThread().getName() + "====>myUnLock");

        atomicReference.compareAndSet(Thread.currentThread(), null);
    }
}
