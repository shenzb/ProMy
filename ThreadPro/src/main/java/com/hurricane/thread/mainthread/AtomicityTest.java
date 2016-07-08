package com.hurricane.thread.mainthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhenbiao.shen on 2016/2/4.
 * 虽然已经同步，但是读取方法不能保证数据是同步的偶数
 */
public class AtomicityTest implements Runnable {
    private int i = 0;

    public int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        try {
            i++;
            Thread.sleep(1000l);
            i++;
            System.out.println("当前值：" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true)
            evenIncrement();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while (true) {
            int val = at.getValue();
            System.out.println("无限循环开始执行：" + val);
            if (val % 2 != 0) {
                System.out.println("出现异常：" + val);
                System.exit(0);
            }
        }
    }
}
