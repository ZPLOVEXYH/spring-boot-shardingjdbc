package com.macky.springbootshardingjdbc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 手动写一个自旋锁
 */
public class TestSpinLock01 {

    public static void main(String[] args) {
        SplinLockThread splinLockThread = new SplinLockThread();

        new Thread(() -> {
            splinLockThread.myLock();
            try {
                // 当a线程获取得到锁之后休眠5秒钟
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                splinLockThread.myUnLock();
            }
        }, "a").start();

        // 休眠1秒钟，保证a线程在b线程之前执行
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            splinLockThread.myLock();
            splinLockThread.myUnLock();
        }, "b").start();
    }
}

class SplinLockThread {

    // 原子应用类
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * CAS加锁
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "====> 进入myLock！");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 如果原子操作的对象不为空，那么就一直循环等待
        while (!atomicReference.compareAndSet(null, thread)) {

        }

        System.out.println(thread.getName() + "====> 加锁！");
    }

    /**
     * CAS解锁
     */
    public void myUnLock() {
        Thread thread = Thread.currentThread();

        if (atomicReference.compareAndSet(thread, null)) {
            System.out.println(thread.getName() + "====> 解锁！");
        }
    }
}
