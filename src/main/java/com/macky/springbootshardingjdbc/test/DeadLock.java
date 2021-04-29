package com.macky.springbootshardingjdbc.test;

/**
 * 使用synchronized
 */
public class DeadLock {

    public static void main(String[] args) {
        MarkUp markUp1 = new MarkUp(1, "灰姑凉");
        MarkUp markUp2 = new MarkUp(0, "白雪公主");

        markUp1.start();
        markUp2.start();
    }
}

// 口红对象
class KouHong {

}

// 镜子对象
class Mirror {
}

// 化妆实现
class MarkUp extends Thread {

    static KouHong kouHong = new KouHong();// 口红对象
    static Mirror mirror = new Mirror();// 镜子对象

    int choice;// 选择

    MarkUp(int choice, String name) {
        super(name);
        this.choice = choice;
    }


    @Override
    public void run() {
        try {
            makeUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 化妆实现接口
     */
    private void makeUp() throws InterruptedException {
        if (choice == 1) {
            synchronized (kouHong) {
                System.out.println(this.getName() + "拿到了口红的锁");
                Thread.sleep(1000);
            }

            synchronized (mirror) {
                System.out.println(this.getName() + "拿到了镜子的锁");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.getName() + "拿到了镜子的锁");
                Thread.sleep(1000);
            }

            synchronized (kouHong) {
                System.out.println(this.getName() + "拿到了口红的锁");
            }
        }
    }
}
