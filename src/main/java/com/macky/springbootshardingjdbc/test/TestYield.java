package com.macky.springbootshardingjdbc.test;

public class TestYield {

    public static void main(String[] args) {
        Yield yield = new Yield();

        new Thread(yield, "a").start();
        new Thread(yield, "b").start();
    }
}

class Yield implements Runnable {

    /**
     * 礼让线程，让当前正在运行的线程暂停，但不阻塞
     * 将线程从运行状态转为就绪状态
     * 让cpu重新调度，礼让不一定成功，要看cpu心情
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始！");
        Thread.yield();// 线程礼让
        System.out.println(Thread.currentThread().getName() + "线程结束！");
    }
}
