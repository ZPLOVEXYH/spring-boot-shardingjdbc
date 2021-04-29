package com.macky.springbootshardingjdbc.test.lambda_test;

import java.util.function.Supplier;

public class SupplierTest01 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//
//                return "Hello World!!!";
//            }
//        };
        Supplier<String> supplier = () -> "Hello World!!!";
        System.out.println(supplier.get());
    }
}
