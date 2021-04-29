package com.macky.springbootshardingjdbc.sync;

public class SyncTest01 {

    public static void main(String[] args){
        try {
            System.out.println(test(0));
        } catch (Exception e) {
            System.out.println("test");
        }
    }

    public static boolean test(int i) throws Exception {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (; ; ) {  // while(true)
                if (i == 1) {
                    return interrupted;
                } else {
                    throw new Exception("sss");
                }
            }
        } finally {
            if (failed)
                test1();
        }
    }

    public static void test1() {
        System.out.println("finally...");
    }

    public static boolean test3() {
        int i = 0;
        if (i == 1) {
            return true;
        } else {

        }

        return false;
    }
}
