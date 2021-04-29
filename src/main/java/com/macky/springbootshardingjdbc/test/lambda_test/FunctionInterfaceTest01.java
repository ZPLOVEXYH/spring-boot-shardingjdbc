package com.macky.springbootshardingjdbc.test.lambda_test;

public class FunctionInterfaceTest01 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        TestFunctionInterface<String> testFunctionInterface = o -> o;
        System.out.println(testFunctionInterface.get("hello world"));
    }
}
