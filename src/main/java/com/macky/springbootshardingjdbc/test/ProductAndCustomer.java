package com.macky.springbootshardingjdbc.test;

/**
 * synchronized、wait、notifyAll版的生产者跟消费者
 */
public class ProductAndCustomer {

    public static void main(String[] args) {
        ProductCustomer pc = new ProductCustomer();

        // A线程为生产者线程，此生产者线程用于生产商品0-
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pc.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        // B线程为消费者线程，此消费者用于消费商品
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pc.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        // C线程为生产者线程，此生产者线程用于生产商品
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pc.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        // D线程为消费者线程，此消费者用于消费商品
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pc.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

/**
 * 业务类（生产者跟消费者）
 */
class ProductCustomer {

    // 生产的商品数量
    private int number = 0;

    /**
     * +1
     * A生产者生成商品+1
     */
    public synchronized void increment() throws InterruptedException {
        while (number > 0) {
            // 如果生成的商品数量大于0，那么生产者等待
            this.wait();
        }

        number++;
        System.out.println(Thread.currentThread().getName() + "生产了商品：" + number + "件");
        // 通知消费者消费商品
        this.notifyAll();
    }

    /**
     * -1
     * B消费者消费商品-1
     */
    public synchronized void decrement() throws InterruptedException {
        while (number <= 0) {
            // 如果消费的商品数量小于0，那么消费者等待，通知生产者生产商品
            this.wait();
        }

        number--;
        System.out.println(Thread.currentThread().getName() + "消费了商品：" + number + "件");
        // 通知生产者生产商品
        this.notifyAll();
    }
}
