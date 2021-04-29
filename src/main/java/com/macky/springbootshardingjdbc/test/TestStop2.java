package com.macky.springbootshardingjdbc.test;

public class TestStop2 {

    public static void test() throws InterruptedException {
        int i = 10;
        while (true) {
            System.out.println(i--);
            Thread.sleep(1000);
            if (i <= 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
