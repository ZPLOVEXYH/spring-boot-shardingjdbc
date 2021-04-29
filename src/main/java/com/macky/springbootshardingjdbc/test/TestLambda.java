package com.macky.springbootshardingjdbc.test;

public class TestLambda {

    public static void main(String[] args) {
        ILike like = new Like();
        like.test();

        like = new Like2();
        like.test();

        TestLambda testLambda = new TestLambda();
        testLambda.funcMethod();

        /**
         * 5、局部内部类
         */
        class Like4 implements ILike {

            @Override
            public void test() {
                System.out.println("i like lambda5");
            }
        }

        Like.TestClass testClass = new Like().new TestClass();
        testClass.testM();

        /**
         * 6、lambda表达式
         */
        like = () -> {
            System.out.println("i like lambda6");
        };

        like.test();


        /**
         * 8、匿名内部类
         */
        like = new ILike() {

            @Override
            public void test() {
                System.out.println("i like lambda8");
            }
        };

        like.test();

        /**
         * 9、匿名内部类，内部方法是有参数的
         */
        ILove love = new ILove() {

            @Override
            public void love(int i, String test) {
                System.out.println("i like lambda" + i);
            }
        };

        love.love(9, "xxx");

        // 10、表达式简化
        love = (i, t) -> System.out.println("i like lambda" + i + t);

        /**
         * 总结：lambda表达式只能在一行代码的情况下才能省略掉大括号{}，如果有多行代码，那么就用代码块{}包裹。
         * 前提接口是函数式接口，@FunctionalInterface
         * 多个参数也可以去掉参数类型，要去掉都去掉，必须加上括号；
         *
         * 函数式接口条件如下：
         * 1、任何接口如果只包含唯一的一个抽象方法。
         */
        love.love(10, ", xxx1");
    }

    public void funcMethod() {
        ILike like = new Like3();
        like.test();
    }

    /**
     * 3、静态内部类
     */
    static class Like2 implements ILike {

        @Override
        public void test() {
            System.out.println("i like lambda3");
        }
    }

    /**
     * 4、非静态的内部类
     */
    class Like3 implements ILike {

        @Override
        public void test() {
            System.out.println("i like lambda4");
        }
    }
}

/**
 * 1、定义函数式接口注解
 */
@FunctionalInterface
interface ILike {
    // 定义一个抽象方法
    void test();
}

class Like implements ILike {

    /**
     * 2、传统的方式来实现函数式接口的方法
     */
    @Override
    public void test() {
        System.out.println("i like lambda2");
    }

    class TestClass {
        public void testM() {
            System.out.println("i like lambda5");
        }
    }
}

@FunctionalInterface
interface ILove {
    public abstract void love(int i, String str);
}

