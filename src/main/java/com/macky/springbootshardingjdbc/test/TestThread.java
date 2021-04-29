package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.*;

// 线程总结
public class TestThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1
        new MyThread().start();

        //2
        new Thread(new MyRunnable()).start();

        //3
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> future = service.submit(new MyCallable());
        String result = future.get();
        System.out.println(result);

        service.shutdown();
    }
}

//1.继承thread类
class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("继承Thread!");
    }
}

//2.实现runnable接口
class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable!");
    }
}

//3.实现callable接口
class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("实现Callable!");
        return "Hello Call";
    }
}





