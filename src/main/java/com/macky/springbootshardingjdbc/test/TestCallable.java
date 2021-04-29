package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.*;

public class TestCallable implements Callable<String> {

    @Override
    public String call() throws Exception {

        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable testCallable = new TestCallable();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> submit1 = executorService.submit(testCallable);
        Future<String> submit2 = executorService.submit(testCallable);
        Future<String> submit3 = executorService.submit(testCallable);

        System.out.println(submit1.get());
        System.out.println(submit2.get());
        System.out.println(submit3.get());

        executorService.shutdown();
    }
}
