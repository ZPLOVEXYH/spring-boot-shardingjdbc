package com.macky.springbootshardingjdbc.test;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * 线程返回值方式介绍：
 * 1、通过主线程延迟
 * 2、通过join方式加入子线程
 */
public class TestReturnValue {

    public static void main(String[] args) throws InterruptedException {
        MyThread01 myThread01 = new MyThread01();
        myThread01.start();

        String username = myThread01.username;
        System.out.println("休眠前的用户名参数值：" + username);

        while (StringUtils.isEmpty(myThread01.username)) {
            // 主线程延迟1秒
            Thread.sleep(1000);
        }

        System.out.println("休眠后的用户名参数值：" + myThread01.username);
    }
}

class MyThread01 extends Thread {

    public String username;

    @Override
    public void run() {
        username = "zhangsan";
    }
}
