package com.macky.springbootshardingjdbc.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全的集合
 */
public class UnsafeList {

    public static void main(String[] args) throws InterruptedException {
        List<String> arrList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (arrList) {
                    arrList.add(Thread.currentThread().getName());
                }
            }).start();
        }

        Thread.sleep(10);

        System.out.println(arrList.size());
    }
}
