package com.macky.springbootshardingjdbc.test;

public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        // 运行子线程
        Join join = new Join();
        Thread thread = new Thread(join);
        thread.start();

        for (int i = 1; i <= 100; i++) {
            if (i == 90) {
                thread.join();
            }
            System.out.println("main线程 " + i);
        }
    }
}

class Join implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println("子线程 " + i);
        }
    }
}
