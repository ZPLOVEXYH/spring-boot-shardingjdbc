package com.macky.springbootshardingjdbc.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TestCallable01 {

    public static void main(String[] args) {
        CallableTest callableTest = new CallableTest();
        FutureTask futureTask = new FutureTask(callableTest);

        new Thread(futureTask, "A").start();
        try {
            boolean done = futureTask.isDone();
            if (!done) {
                System.out.println("线程尚未结束");
            }
            Object o = futureTask.get();// 会产生阻塞，把他放在最后，或者使用异步通信来处理！
            done = futureTask.isDone();
            System.out.println(done);
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println("xxxx");
    }
}

class CallableTest implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("call()");
        TimeUnit.SECONDS.sleep(4);

        return "hello call()";
    }
}
