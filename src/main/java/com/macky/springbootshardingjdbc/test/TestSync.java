package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSync {

    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                zoo.buy();
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                zoo.buy();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                zoo.buy();
            }
        }, "c").start();
    }
}

class Zoo {
    private int ticketNums = 50;
    private Lock lock = new ReentrantLock();

    public void buy() {
        lock.lock();
        try {
            // abc
            if (ticketNums > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + ticketNums-- + "张票，剩余：" + ticketNums + "张票");
            }
        } finally {
            lock.unlock();
        }
    }

}
