package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    public static void main(String[] args) {
        Lock lock = new Lock();

        new Thread(lock, "a").start();
        new Thread(lock, "b").start();
        new Thread(lock, "c").start();
    }
}

class Lock implements Runnable {

    private int ticketNums = 10;

    private java.util.concurrent.locks.Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                // 执行不安全代码之前先加锁
                lock.lock();
                // 执行业务代码块
                if (ticketNums > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "====>" + ticketNums--);
                } else {
                    break;
                }
            } finally {
                lock.unlock();// 业务代码执行完毕之后解锁
            }
        }
    }
}
