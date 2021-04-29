package com.macky.springbootshardingjdbc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock同步锁-互斥锁-公平锁-非公平锁
 */
public class LockExample02 {

    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        // 创建线程1
        Thread thread01 = new Thread(() -> {
            System.out.println("t1");
            // 加锁
            lock.lock();
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "===>获取得到锁！");
                logic();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
                lock.unlock();
            }
        }, "a");


        // 启动线程1
        thread01.start();

//        try {
//            // 主线程休眠一段时间，保证线程1在线程2之前执行
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // 创建线程2
//        Thread thread02 = new Thread(() -> {
//            System.out.println("t2");
//            // 加锁
//            lock.lock();
//            try {
//                System.out.println(Thread.currentThread().getName() + "===>获取得到锁！");
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                // 释放锁
//                lock.unlock();
//            }
//        }, "b");
//
//        // 启动线程2
//        thread02.start();


    }

    /**
     * 获取得到锁之后的业务操作
     */
    public static void logic() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
