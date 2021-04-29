package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.TimeUnit;

public class Test01 {

    public static void main(String[] args) {
        Phone phone = new Phone();

        // A线程发短信
        new Thread(() -> {
            phone.sendSMS();
        }, "A").start();

        // B线程打电话
        new Thread(() -> {
            phone.call();
        }, "B").start();

        // C线程say hello
        new Thread(() -> {
            phone.hello();
        }, "C").start();
    }
}

class Phone {

    /**
     * 发短信
     */
    public synchronized void sendSMS() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发短信");
    }


    /**
     * 打电话
     */
    public synchronized void call() {
        System.out.println("打电话");
    }

    /**
     * hello
     */
    public void hello() {
        System.out.println("hello");
    }
}
