package com.macky.springbootshardingjdbc.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockTest01 {

    private static AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        System.out.println(atomicReference.get());

        atomicReference.compareAndSet(null, new Thread(() -> {
            System.out.println("test");
        }, "a"));

        atomicReference.get().start();

    }
}
