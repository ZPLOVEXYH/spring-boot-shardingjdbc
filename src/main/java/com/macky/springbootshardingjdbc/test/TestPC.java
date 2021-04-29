package com.macky.springbootshardingjdbc.test;

/**
 * 测试生产者和消费者
 */
public class TestPC {

    public static void main(String[] args) {
        SyncContainer syncContainer = new SyncContainer();

        new Product(syncContainer).start();
        new Consumer(syncContainer).start();
    }
}

/**
 * 生产者生成Chicken
 */
class Product extends Thread {

    private SyncContainer syncContainer;

    public Product(SyncContainer syncContainer) {
        this.syncContainer = syncContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // 生产者生产商品
            Chicken chicken = new Chicken(i);
            syncContainer.push(chicken);
            System.out.println("生产者生产了：" + i + "只鸡！");
        }
    }
}

/**
 * 消费者消费Chicken
 */
class Consumer extends Thread {

    private SyncContainer syncContainer;

    public Consumer(SyncContainer syncContainer) {
        this.syncContainer = syncContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Chicken pop = syncContainer.pop();
            System.out.println("消费者消费了：" + pop.id + "只鸡！");
        }
    }
}

/**
 * 生产的产品
 */
class Chicken {
    public int id;// 产品id

    public Chicken(int id) {
        this.id = id;
    }
}

/**
 * 缓冲区容器
 */
class SyncContainer {
    Chicken[] chicken = new Chicken[10];// 缓冲区容器存放10个商品
    int count = 0;// 商品的件数

    /**
     * 生产者将生产好的商品添加到缓存区容器
     *
     * @param ken
     */
    public synchronized void push(Chicken ken) {
        // 如果容器已满，那么生产者等待，通知消费者消费产品
        if (count == chicken.length) {
            // 生产者等待，通知消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果缓冲区容器有商品，那么通知消费者进行消费
        chicken[count] = ken;
        count++;

        // 通知消费者进行消费
        this.notifyAll();
    }

    /**
     * 消费者消费缓冲区容器中的商品
     */
    public synchronized Chicken pop() {
        // 如果容器没有了商品，那么消费者等待，通知生产者生产产品
        if (count == 0) {
            // 消费者等待，通知生产者生成产品
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        Chicken chi = chicken[count];

        this.notifyAll();

        return chi;
    }

}




