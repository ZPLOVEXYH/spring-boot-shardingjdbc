package com.macky.springbootshardingjdbc.test.lambda_test;

import java.util.function.Consumer;

/**
 * 消费型函数接口，有入参，无出参
 */
public class ConsumerTest01 {
    public static void main(String[] args) {
//        test2();

        Phone01 phone01 = new Phone01();
        phone01.setFlag(true);

        System.out.println(phone01.isFlag());
    }


    public static void test1() {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String o) {
//                System.out.println("输出传入的参数为：" + o);
//            }
//        };

        Consumer<String> consumer = o -> System.out.println("输出传入的参数为：" + o);

        consumer.accept("hello world");
    }

    public static void test2() {
        Consumer<String> consumer = o -> System.out.println("输出传入的参数1为：" + o);
        Consumer<String> consumer2 = o -> System.out.println("输出传入的参数2为：" + o);

        consumer.andThen(consumer2).accept("test");

    }



}

class Phone01 {
    private boolean flag;
    private String name;
    private String eEmail;

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
