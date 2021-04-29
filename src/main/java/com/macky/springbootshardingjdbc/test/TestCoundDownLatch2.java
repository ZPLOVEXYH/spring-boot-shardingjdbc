package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.CountDownLatch;

/**
 * 场景1 百米赛跑决赛
 */
public class TestCoundDownLatch2 {

    public static void main(String[] args) {
        // 使用的场景：百米赛跑决赛
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 1; i <= 100; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    countDownLatch.await();// 各就各位，预备！ 调用该方法的线程会被阻塞，知道计数器的值从10->0,才会执行下一步操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行！！！");
            }).start();
        }


        countDownLatch.countDown();// 调用该方法，计数器的线程数-1

        System.out.println("end.....");

        System.out.println("比赛结束！！！");
    }
}
