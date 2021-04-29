package com.macky.springbootshardingjdbc.atomic;

/**
 * @author ZhangPeng
 * @time 2021-04-12 13:37
 */
public class AtomicityTest01 {

    public static void main(String[] args) {
        new Thread(new ChangeT(100L)).start();
        new Thread(new ChangeT(-244L)).start();

        new Thread(new ReadT()).start();
    }

    public static long t = 0;

    /**
     * 改变全局变量的值
     *
     * @Author ZhangPeng
     * @Date 2021/4/12
     **/
    public static class ChangeT implements Runnable {

        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                AtomicityTest01.t = to;
                // 线程礼让
                Thread.yield();
            }
        }
    }

    /**
     * 读取全局变量的值
     *
     * @Author ZhangPeng
     * @Date 2021/4/12
     **/
    public static class ReadT implements Runnable {

        @Override
        public void run() {
            while (true) {
                long tmp = AtomicityTest01.t;
                if (tmp != 100L && tmp != -244) {
                    System.out.println(tmp);
                }

                Thread.yield();
            }
        }
    }
}
