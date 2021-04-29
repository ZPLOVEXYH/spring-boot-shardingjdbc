package com.macky.springbootshardingjdbc.singlemole;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleTest01 {

    public static void main(String[] args) {

        test1();

    }

    public static void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        System.out.println(atomicInteger.compareAndSet(2020, 2021));

        System.out.println(atomicInteger.get());


//        System.out.println(atomicInteger.compareAndSet(2021, 2020));
//
//        System.out.println(atomicInteger.get());

        // 预期的值被改动过了，所以不会执行原子性操作
        System.out.println(atomicInteger.compareAndSet(2020, 6666));
        System.out.println(atomicInteger.get());
    }

    public static void test2() {

    }
}
