package com.macky.springbootshardingjdbc.test.lambda_test;

import java.util.function.Function;

/**
 * 函数式接口
 */
public class FunctionTest01 {

    public static void main(String[] args) {
        test4();
    }

    /**
     * java 8之前的匿名内部类的写法
     */
    public static void test1() {
        // java 8之前的匿名内部类的写法
        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer i) {
                if (0 == i) {
                    return "success";
                } else {
                    return "false";
                }
            }
        };

        System.out.println(function.apply(1));
    }

    /**
     * 使用java8 lambda的写法
     */
    public static void test2() {
        Function<Integer, String> function = i -> {
            return "success";
        };

        System.out.println(function.apply(1));

    }

    /**
     * 使用function.compose
     */
    public static void test3() {
        // 使用function.compose
        Function<Integer, Integer> function1 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        };

        Function<Integer, Integer> function2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        };

        System.out.println("function.compose方法得到的结果为：" + function1.compose(function2).apply(2));

    }

    /**
     * 使用function.andThen
     */
    public static void test4() {
        // 使用function.compose
        Function<Integer, Integer> function1 = (integer) -> integer * 2;
        Function<Integer, Integer> function2 = (integer) -> integer * integer;

        System.out.println("function.andThen方法得到的结果为：" + function1.andThen(function2).apply(2));

    }
}
