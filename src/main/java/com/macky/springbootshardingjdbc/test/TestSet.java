package com.macky.springbootshardingjdbc.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TestSet {

    public static void main(String[] args) {
        // set不安全性
//        Set<String> set = new HashSet<>();

        /**
         * 1、Set安全性解决方案一：Collections.synchronizedSet()方法
         */
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));

                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
