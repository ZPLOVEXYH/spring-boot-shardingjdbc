package com.macky.springbootshardingjdbc.test;

public class UnsafeBank {

    public static void main(String[] args) {
        Account account = new Account("份子钱", 100);

        DrawMoney drawMoney = new DrawMoney(account, 50, "我");
        drawMoney.start();

        DrawMoney drawMoney2 = new DrawMoney(account, 100, "老婆");
        drawMoney2.start();
    }
}

// 银行账户
class Account {
    public String accountName;// 账户名称
    public int money;// 账户的余额

    public Account(String accountName, int money) {
        this.accountName = accountName;
        this.money = money;
    }
}

class DrawMoney extends Thread {
    private Account account;
    private int drawMoney; // 取出来的钱
    private int blance;// 手里的钱

    public DrawMoney(Account account, int drawMoney, String name) {
        super(name);
        this.account = account;
        this.drawMoney = drawMoney;
    }

    @Override
    public void run() {
        synchronized (account) {
            // 判断银行卡钱够不够
            if (account.money - drawMoney < 0) {
                System.out.println(this.getName() + "钱不够，取不了。");
                return;
            }

            // sleep模拟实际业务执行时间
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 账户减去之后的钱=账户减去之前的钱-取出来的钱
            account.money = account.money - drawMoney;
            // 手里面的钱 = 之前取出来的钱 + 当笔取出来的钱
            blance = blance + drawMoney;

            System.out.println(account.accountName + "余额为：" + account.money);
            System.out.println(this.getName() + "取出来了" + blance);

        }
    }

}



