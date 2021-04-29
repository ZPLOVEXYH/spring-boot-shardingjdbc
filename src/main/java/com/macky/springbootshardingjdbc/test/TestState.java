package com.macky.springbootshardingjdbc.test;

public class TestState {

    public static void main(String[] args) throws InterruptedException {
        TestThreadState testThreadState = new TestThreadState();
        // NEW状态
        Thread thread = new Thread(testThreadState);
        Thread.State state = thread.getState();
        System.out.println(state);

        // RUNNABLE状态
        thread.start();
        state = thread.getState();
        System.out.println(state);

        while (Thread.State.TERMINATED != state) {
            Thread.sleep(1000);

            state = thread.getState();
            System.out.println(state);
        }

    }
}

class TestThreadState implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
