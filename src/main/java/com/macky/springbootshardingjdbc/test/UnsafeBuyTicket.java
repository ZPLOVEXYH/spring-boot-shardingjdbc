package com.macky.springbootshardingjdbc.test;

/**
 * 不安全的买票
 */
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        // 售票对象
        BuyTicket buyTicket = new BuyTicket();

        // 三个人去买票
        new Thread(buyTicket, "a").start();
        new Thread(buyTicket, "b").start();
        new Thread(buyTicket, "c").start();
    }
}

class BuyTicket implements Runnable {
    private int ticketTotals = 10; // 售票总量
    private boolean flag = true; // 是否正在售票

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 买票
     */
    private void buy() throws InterruptedException {
        synchronized (this) {
            if (ticketTotals <= 0) {
                flag = false;
                return;
            }

            Thread.sleep(100);

            System.out.println(Thread.currentThread().getName() + "拿到了第" + (ticketTotals--) + "张票");
        }
    }
}
