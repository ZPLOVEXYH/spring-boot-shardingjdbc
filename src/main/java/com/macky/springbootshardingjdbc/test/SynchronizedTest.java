package com.macky.springbootshardingjdbc.test;

public class SynchronizedTest {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // @FunctionInterface (参数) -> {代码}
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "test1").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "test2").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "test3").start();
    }


}

class Ticket {

    // 门票总票数
    private int number = 50;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了：" + (number--) + "张票，剩余：" + number + "张票。");
        }
    }
}
