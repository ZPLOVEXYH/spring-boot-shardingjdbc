package com.macky.springbootshardingjdbc.volatile_test;

import java.util.concurrent.TimeUnit;

/**
 * volatile java关键词的作用：
 * 1、保证可见性；
 * 2、不保证原子性；
 * 3、禁止指令重排；
 */
public class VolatileTest01 {

    private volatile static int num = 0;

    public static void main(String[] args) {
        test1();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 重新给num赋值，跳出死循环
        num = 1;
        System.out.println(num);
    }

    // 测试volatile的可见性
    private static void test1() {
        new Thread(() -> {
            while (0 == num) {

            }
        }).start();

    }
}
