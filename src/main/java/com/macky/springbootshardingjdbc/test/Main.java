package com.macky.springbootshardingjdbc.test;

public class Main {

    public static void main(String[] args) {
        Son s = new Son();
    }
}

// 书类，用于测试对象成员变量
class Book {
    public Book(String user) {
        System.out.println(user + "成员变量");
    }
}

// 子类
class Son extends Fa {
    static Book book = new Book("子类静态");

    static {
        System.out.println("子类静态代码块");
    }

    Book sBook = new Book("子类");

    {
        System.out.println("子类非静态代码块");
    }

    public Son() {
        System.out.println("子类构造方法");
    }
}

// 父类
class Fa {
    static Book book = new Book("父类静态");

    static {
        System.out.println("父类静态代码块");
    }

    Book fBook = new Book("父类");

    {
        System.out.println("父类非静态代码块");
    }

    public Fa() {
        System.out.println("父类构造方法");
    }
}
