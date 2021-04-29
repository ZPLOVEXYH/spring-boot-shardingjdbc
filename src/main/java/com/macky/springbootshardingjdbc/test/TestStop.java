package com.macky.springbootshardingjdbc.test;

public class TestStop implements Runnable {

    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        System.out.println("test");
        while(flag) {
            System.out.println("run... thread" + i++);
        }
    }

    // 此方法停止线程运行，转换flag标志位的值
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) throws InterruptedException {
        TestStop testStop = new TestStop();

        new Thread(testStop).start();

        Thread.sleep(10);
        for (int i = 0; i < 10000; i++) {
            if (i == 9999) {
                testStop.stop();
                System.out.println("线程已停止");
            }
        }
    }
}
