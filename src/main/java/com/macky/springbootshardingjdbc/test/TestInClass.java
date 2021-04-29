package com.macky.springbootshardingjdbc.test;

public class TestInClass {

    private String username = "lisi";

    public static void main(String[] args) {
        House house = new House();
        house.setName("zhangsan");
        house.setAge(21);

        new Thread(() -> {

            house.setName("test");
            house.setAge(11);

//            try {
//                Thread.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            System.out.println(Thread.currentThread().getName() + "==>" + house.getName());
            System.out.println(Thread.currentThread().getName() + "==>" + house.getAge());

        }, "a").start();

        new Thread(() -> {
            house.setName("test");
            house.setAge(11);

//            try {
//                Thread.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            System.out.println(Thread.currentThread().getName() + "==>" + house.getName());
            System.out.println(Thread.currentThread().getName() + "==>" + house.getAge());

        }, "b").start();

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(house.getName());
        System.out.println(house.getAge());
    }
}


class House {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
