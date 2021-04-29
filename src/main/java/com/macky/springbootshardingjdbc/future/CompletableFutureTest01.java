package com.macky.springbootshardingjdbc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest01 {
    public static void main(String[] args) {
        try {
            test7();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 异步回调，无返回值
     */
    public static void test1() {
        // 异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "===>异步回调");
        });

        try {
            completableFuture.get();// 等待线程执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "===>主线程");
    }

    /**
     * 异步回调，有返回值
     */
    public static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "==> hello,1024");

            int i = 2 / 0;
            return 200;
        });

        // 当异步回执完成时，返回的结果值或者异常值
        System.out.println("返回的异常结果为：" +
                completableFuture.whenComplete((t, u) -> {
                })
                        .exceptionally((e) -> {
                            System.out.println("输出的错误内容为：" + e.getMessage());
                            return 404;
                        }).get()
        );
    }

    /**
     * 测试ThenApply
     */
    public static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(CompletableFutureTest01::randomInteger).thenApply(x -> x * 8);

        TimeUnit.SECONDS.sleep(2);
        System.out.println("异步回调执行后的结果为：" + completableFuture.get());
    }

    /**
     * 测试ThenAccept
     */
    public static void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(CompletableFutureTest01::randomInteger).thenAccept(x -> {
            System.out.println(Thread.currentThread().getName() + "===>" + x);
        });

        TimeUnit.SECONDS.sleep(2);
        System.out.println("异步回调执行后的结果为：" + completableFuture.get());
    }

    /**
     * 测试ThenAcceptAsync
     */
    public static void test5() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return CompletableFutureTest01.randomInteger();
        }).thenAccept(x -> {
            System.out.println(Thread.currentThread().getName() + "===>" + x);
        });

        TimeUnit.SECONDS.sleep(2);
        System.out.println("异步回调执行后的结果为：" + completableFuture.get());
    }

    public static Integer randomInteger() {
        return 10;
    }

    /**
     * 测试ThenAcceptAsync
     */
    public static void test6() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return CompletableFutureTest01.randomInteger();
        }).thenAcceptAsync(x -> {
            System.out.println(Thread.currentThread().getName() + "===>" + x);
        });

        TimeUnit.SECONDS.sleep(2);
        System.out.println("异步回调执行后的结果为：" + completableFuture.get());
    }

    /**
     * 测试ThenCompose的执行线程
     */
    public static void test7() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return CompletableFutureTest01.randomInteger();
        }).thenCompose(x -> {
            System.out.println(Thread.currentThread().getName() + "===>" + x);
            return CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "!!!");
                return x * 2;
            });
        });

        TimeUnit.SECONDS.sleep(2);
        System.out.println("异步回调执行后的结果为：" + completableFuture.get());
    }
}
