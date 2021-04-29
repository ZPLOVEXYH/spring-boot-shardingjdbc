package com.macky.springbootshardingjdbc.test;

/**
 * 线程优先级
 */
public class TestPriority {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "====>" + Thread.currentThread().getPriority());

        Priority priority = new Priority();

        Thread thread1 = new Thread(priority);
        Thread thread2 = new Thread(priority);
        Thread thread3 = new Thread(priority);
        Thread thread4 = new Thread(priority);
        Thread thread5 = new Thread(priority);

        thread1.setPriority(1);
        thread1.start();

        thread2.setPriority(5);
        thread2.start();

        thread3.setPriority(4);
        thread3.start();

//        thread4.setPriority(-1);
//        thread4.start();
//
//        thread5.setPriority(11);
//        thread5.start();

    }
}

class Priority implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "====>" + Thread.currentThread().getPriority());
    }
}
