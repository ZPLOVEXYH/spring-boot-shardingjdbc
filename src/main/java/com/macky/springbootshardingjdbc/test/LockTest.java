package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();

        // @FunctionInterface (参数) -> {代码}
        new Thread(() -> {
            for (int i = 0; i < 60; i++) ticket.sale();
        }, "test1").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) ticket.sale();
        }, "test2").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) ticket.sale();
        }, "test3").start();
    }

}

class Ticket2 {

    // 门票总票数
    private int number = 50;

    // 1、声明锁
    Lock lock = new ReentrantLock();

    public void sale() {
        // 2、加锁
        lock.lock();
        try {
            // 编写业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了：" + (number--) + "张票，剩余：" + number + "张票。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 3、解锁
            lock.unlock();
        }
    }
}
