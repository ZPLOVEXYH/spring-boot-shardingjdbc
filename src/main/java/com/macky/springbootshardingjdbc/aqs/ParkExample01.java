package com.macky.springbootshardingjdbc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 测试unsalf类的park方法
 */
public class ParkExample01 {

    public static void main(String[] args) {
        System.out.println("-------------m1--------------");
        // 创建线程
        Thread thread = new Thread(() -> {
            System.out.println("-------------t1--------------");
            // 当前线程被禁用，并处于休眠状态
            LockSupport.park();
            System.out.println("-------------t2--------------");

        }, "t1");

        // 启动线程
        thread.start();

        try {
            // 让主线程休眠5秒钟再执行
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-------------m2--------------");
        // 要取消驻留的线程，或者为null，但是这种情况，此操作无效
        LockSupport.unpark(thread);

    }
}
