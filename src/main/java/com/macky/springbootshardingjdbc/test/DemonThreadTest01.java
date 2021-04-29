package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.TimeUnit;

public class DemonThreadTest01 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("main start ...");

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test-thread");
        });
        thread.start();
        thread.join();

        System.out.println("main end ...");


    }

    public static void test1() {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }, "test-thread");

        thread.start();
    }
}
