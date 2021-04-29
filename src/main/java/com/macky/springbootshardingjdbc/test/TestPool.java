package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 测试线程池
public class TestPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.execute(new MyPool());
        executorService.shutdown();
    }
}

class MyPool implements Runnable {

    @Override
    public void run() {
        System.out.println("当前的线程名称为：" + Thread.currentThread().getName());
    }
}


