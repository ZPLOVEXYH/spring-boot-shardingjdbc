package com.macky.springbootshardingjdbc.test;

/**
 * 线程返回值方式介绍：
 * 1、通过主线程延迟
 * 2、通过join方式加入子线程
 */
public class TestReturnValue02 {

    public static void main(String[] args) {
        MyThread02 myThread02 = new MyThread02();
        myThread02.start();

        System.out.println(myThread02.testname);
//        try {
        //// 获取子线程的返回值：Thread的join方法来阻塞主线程，直到子线程返回
//            myThread02.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(myThread02.testname);
    }
}

class MyThread02 extends Thread {
    public String testname;

    @Override
    public void run() {
        testname = "zhangsan";
    }
}