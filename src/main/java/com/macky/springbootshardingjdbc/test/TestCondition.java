package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock跟condition版本的生产者跟消费者
 */
public class TestCondition {

    public static void main(String[] args) {
        PandC pandC = new PandC();

        // a线程进行生产商品
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pandC.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a").start();

        // b线程进行消费商品
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pandC.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "b").start();

        // c线程进行生产商品
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pandC.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "c").start();

        // d线程进行消费商品
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pandC.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "d").start();
    }
}

/**
 * 生产者生产商品，消费者消费商品
 */
class PandC {

    // 生产的商品数量
    private int numbers = 0;
    // lock锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 生产者生产商品
     */
    public void put() throws InterruptedException {
        lock.lock();

        try {
            while (numbers > 0) {
                // 如果生产者生产的商品大于0，那么生产者保持等待，并通知消费者来消费
//              this.wait();
                condition.await();
            }

            // 生产者生产商品+1
            numbers++;
            System.out.println(Thread.currentThread().getName() + "生产了：" + numbers + "件商品！");
            // +1完之后，通知消费者进行消费
//        this.notifyAll();
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 消费者消费商品
     */
    public void pop() throws InterruptedException {
        lock.lock();
        try {
            while (numbers <= 0) {
                // 如果消费者消费的商品大于0，那么消费者保持等待，并通知生产者生产
    //            this.wait();
                condition.await();
            }

            // 消费者消费商品-1
            numbers--;
            System.out.println(Thread.currentThread().getName() + "消费了：" + numbers + "件商品！");
            // -1完之后，通知生产者进行生产
//        this.notifyAll();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

