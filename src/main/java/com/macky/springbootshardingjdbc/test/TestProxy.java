package com.macky.springbootshardingjdbc.test;

public class TestProxy {
    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new WeddingCompany());
        weddingCompany.happyMarry();
    }

}

interface Marry {
    void happyMarry();
}

class You implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("秦老师要结婚了，超开心的！");
    }
}

class WeddingCompany implements Marry {

    private Marry target;

    public WeddingCompany() {
    }

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        target.happyMarry();
        after();
    }

    private void after() {
        System.out.println("婚礼结束了，需要结尾款了！");
    }

    private void before() {
        System.out.println("婚礼开始了，提前准备准备！");
    }
}
