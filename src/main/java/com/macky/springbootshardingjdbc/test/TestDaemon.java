package com.macky.springbootshardingjdbc.test;

public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        Me me = new Me();

        Thread godThread = new Thread(god);
        godThread.setDaemon(true);
        godThread.start();

        Thread meThread = new Thread(me);
        meThread.start();
    }
}

// 上帝 守护线程
class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝一直保佑着你");
        }
    }
}


// 你 用户线程
class Me implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("希望每一天你都开开心心的");
        }
        System.out.println("========goods bye world========");
    }
}