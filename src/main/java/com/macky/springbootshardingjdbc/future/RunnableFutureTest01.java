package com.macky.springbootshardingjdbc.future;

import java.util.concurrent.*;

/**
 * 测试RunnableFuture
 */
public class RunnableFutureTest01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 通过实现callable接口的方式来创建线程
//        test1();

        // 通过线程池的方式来创建线程
//        test2();

        test3();
    }

    /**
     * 通过实现callable接口的方式来创建线程
     */
    public static void test1() {
        CallableTest01 callableTest01 = new CallableTest01();
        FutureTask futureTask = new FutureTask(callableTest01);
        futureTask.run();

        try {
            System.out.println("1.输出子线程的返回值为：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过线程池的方式来创建线程
     */
    public static void test2() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );

        Future future = threadPool.submit(new CallableTest01());
        try {
            System.out.println("2.输出的线程返回值为：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }

    /**
     * 通过Executors来创建线程池的方式来创建线程
     */
    public static void test3() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        Future future = threadPool.submit(new CallableTest01());
        while (future.isDone()) {
            System.out.println(future.get());
            TimeUnit.SECONDS.sleep(2);

            break;
        }

        threadPool.shutdown();
    }
}

class CallableTest01 implements Callable {

    @Override
    public String call() throws Exception {

        return "hello world!!!";
    }
}