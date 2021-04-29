package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition2 {

    public static void main(String[] args) {
        ConditionLock conditionLock = new ConditionLock();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                conditionLock.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                conditionLock.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                conditionLock.printC();
            }
        }, "C").start();
    }
}

/**
 * 生产者跟消费者模式
 */
class ConditionLock {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1; // 当number为1,执行A线程；当number为2,执行B线程；当number为3,执行C线程；

    public void printA() {
        lock.lock();
        try {
            // 循环判断等待-》执行业务代码-》通知其他线程
            while (number != 1) {
                condition1.await();
            }

            System.out.println(Thread.currentThread().getName() + "====>AAAAAA");
            number = 2;
            // 通过对象监听器唤醒B线程执行
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB() {
        lock.lock();
        try {
            // 循环判断等待-》执行业务代码-》通知其他线程
            while (number != 2) {
                condition2.await();
            }

            System.out.println(Thread.currentThread().getName() + "====>BBBBBB");
            number = 3;
            // 通过对象监听器唤醒C线程执行
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            // 循环判断等待-》执行业务代码-》通知其他线程
            while (number != 3) {
                condition3.await();
            }

            System.out.println(Thread.currentThread().getName() + "====>CCCCCC");
            number = 1;
            // 通过对象监听器唤醒A线程执行
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}