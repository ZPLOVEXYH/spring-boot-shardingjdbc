package com.macky.springbootshardingjdbc.test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试集合类
 */
public class TestList {

    /**
     * 集合类不安全
     *
     * @param args
     */
    public static void main(String[] args) {
        // 线程不安全
        // List<String> list = new ArrayList<>();

        /**
         * 1、线程安全的解决方案1，使用的是synchronized
         * 关键词修饰的方法，锁的是方法的调用方：list；
         *
         * 缺点：效率低
         */
        // List<String> list = new Vector<>();

        /**
         * 2、线程安全的解决方案2，使用Collections.synchronizedList()方法修饰；
         *
         * 缺点：效率低
         */
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        /**
         * 3、线程安全的解决方案3，使用CopyOnWriteArrayList()实现类
         * 写入时复制，简称COW，计算机程序设计领域的一种优化策略；
         * 在写入的时候避免覆盖，造成数据问题！
         * 读写分离
         *
         * 优点：效率高，灵活性好
         */
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));

                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
