package com.macky.springbootshardingjdbc.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁 deadlock
 */
public class SpinLockTest03 {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        MyRunnable runnableA = new MyRunnable(lockA, lockB);
        MyRunnable runnableB = new MyRunnable(lockB, lockA);


        new Thread(runnableA, "a").start();
        new Thread(runnableB, "b").start();
    }
}

class MyRunnable implements Runnable {

    private String lockA;
    private String lockB;

    public MyRunnable(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + ", lockd=" + lockA + ", get=" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {

            }
        }
    }
}
