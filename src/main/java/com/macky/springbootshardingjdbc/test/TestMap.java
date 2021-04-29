package com.macky.springbootshardingjdbc.test;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {

    public static void main(String[] args) {
        // Map线程不安全性，多线程情况下会出现并发修改异常错误：ConcurrentModificationException
//        Map<String, String> map = new HashMap<>();

//        /**
//         * 1、解决Map线程安全性问方案1，Collections.synchronizedMap()方法；
//         */
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        /**
         * 2、解决Map线程安全性方案2：实现并发hashmap,concurrentHashMap()
         */
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0, 5), UUID.randomUUID().toString().substring(0, 6));

                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
