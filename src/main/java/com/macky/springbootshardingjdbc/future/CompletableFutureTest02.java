package com.macky.springbootshardingjdbc.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 异步回调
 */
public class CompletableFutureTest02 {

    private static List<Book> bookList = new ArrayList<>();

    static {
        Book book = new Book();
        book.setName("知识改变命运");
        book.setPrice(45.9);
        bookList.add(book);

        Book book2 = new Book();
        book2.setName("知识改变命运2");
        book2.setPrice(43.9);
        bookList.add(book2);

        Book book3 = new Book();
        book3.setName("知识改变命运3");
        book3.setPrice(48.9);
        bookList.add(book3);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> stringList = test1();
        stringList.forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("耗时=====>" + (end - start) + "ms");

//        long start = System.currentTimeMillis();
//        List<String> stringList = test2();
//        stringList.forEach(System.out::println);
//        long end = System.currentTimeMillis();
//        System.out.println("耗时=====>" + (end - start) + "ms");
    }

    /**
     * 异步回调
     */
    public static List<String> test1() {
        List<CompletableFuture<String>> completableFutureList = bookList.stream().map(book -> {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                Double price = book.getPrice();
                Integer formatPrice = price.intValue();

                return "异步回调格式化价格：" + formatPrice;
            });//.exceptionally(e -> e.getMessage());

            return completableFuture;
        }).collect(Collectors.toList());

        return completableFutureList.stream()
                .map(x -> x.join()) // 获取结果不会抛出异常
                .collect(Collectors.toList());
    }

    /**
     * 同步回调
     */
    public static List<String> test2() {
        return bookList.stream().map(x -> {
            Double price = x.getPrice();
            Integer formatPrice = price.intValue();

            return "异步回调格式化价格：" + formatPrice;
        }).collect(Collectors.toList());
    }
}

class Book {
    private String name;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
