package com.macky.springbootshardingjdbc.rabbitmq;

/**
 * @author ZhangPeng
 * @time 2021-04-23 10:08
 */
public class RpcServer {

    public static void main(String[] args) {

    }

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
