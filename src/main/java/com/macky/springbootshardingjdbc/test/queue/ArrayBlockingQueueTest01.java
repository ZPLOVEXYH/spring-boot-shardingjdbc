package com.macky.springbootshardingjdbc.test.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列的四组api
 * 1、异常报错
 * 2、不报错，有返回值
 * 3、一直阻塞
 * 4、固定时间阻塞，时间过了字段退出
 */
public class ArrayBlockingQueueTest01 {

    public static void main(String[] args) throws InterruptedException {
        // 初始化需要提供队列的容量大小
        test4();
    }

    /**
     * 抛出异常错误，符合先进先出原则FIFO
     */
    private static void test1() {
        // 初始化需要提供队列的容量大小
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
//        blockingQueue.add("d");

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.element());
        System.out.println("==========================");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
    }

    /**
     * 不抛出异常，有返回值
     */
    private static void test2() throws InterruptedException {
        // 初始化需要提供队列的容量大小
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 插入的数据超过队列初始化的容量，不会抛出异常，返回false,插入失败！
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 阻塞队列
     */
    private static void test3() throws InterruptedException {
        // 初始化需要提供队列的容量大小
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        System.out.println(blockingQueue.poll());
        blockingQueue.put("d");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.take());

    }

    /**
     * 阻塞队列，超时退出
     */
    private static void test4() throws InterruptedException {
        // 初始化需要提供队列的容量大小
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }
}
