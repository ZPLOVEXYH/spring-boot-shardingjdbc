package com.macky.springbootshardingjdbc.test.lambda_test;

import java.util.function.Predicate;

/**
 * 断定型函数接口
 */
public class PredicateTest01 {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 断定型函数接口
     */
    public static void test1() {
//        Predicate<Integer> predicate = new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {
//                if (0 == integer) return true;
//                return false;
//            }
//        };

        Predicate<Integer> predicate = (integer) -> true;
        Predicate<Integer> predicate2 = (integer) -> false;

        System.out.println(Predicate.isEqual(3).test(3));


    }


}
