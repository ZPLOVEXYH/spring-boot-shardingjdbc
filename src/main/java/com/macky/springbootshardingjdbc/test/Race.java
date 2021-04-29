package com.macky.springbootshardingjdbc.test;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

// 赛道
public class Race implements Runnable {

    // 胜利者
    private static String winner;

    // 龟兔赛跑
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            String name = Thread.currentThread().getName();
            if ("兔子".equals(name)) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean flag = gameOver(i);
            // 比赛结束，停止游戏
            if (flag) {
                break;
            }

            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

    // 游戏结束方法
    private boolean gameOver(int steps) {
        if (StringUtils.isNotEmpty(winner)) {
            return true;
        } else {
            if (steps >= 100) {
                winner = Thread.currentThread().getName();
                System.out.println("游戏结束：" + winner + "获胜！");
                return true;
            }
        }

        return false;
    }

    // 主程序入口
    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
