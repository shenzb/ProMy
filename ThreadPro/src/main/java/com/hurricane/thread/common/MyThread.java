package com.hurricane.thread.common;

import com.hurricane.thread.pojo.Data;

/**
 * Created by zhenbiao.shen on 2016/2/4.
 */
public class MyThread implements Runnable {
    private Data data;

    public MyThread(Data d) {
        data = d;
    }


    public void run() {
        while (true) {
            String threadName = Thread.currentThread().getName();
            int val = data.getSyncEvenInner(threadName);
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
            }
        }
    }
}
