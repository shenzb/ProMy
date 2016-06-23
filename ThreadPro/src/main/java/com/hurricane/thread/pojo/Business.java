package com.hurricane.thread.pojo;

/**
 * Created by zhenbiao.shen on 2016/2/4.
 */
public class Business {
    private boolean bShouldSub = true;

    public synchronized void sub(int i) {
        //如果bussiness调用sub的话，则【!bShouldSub =false，bShouldSub=true】;
        //然后主线程main为等待，执行完sub后就bShouldSub=false；唤醒主线程main。
        while (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 2; j++) {
            System.out.println("sub com.hurricane.thread.thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = false;
        //唤醒main
        this.notify();
    }

    public synchronized void main(int i) {
        while (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 3; j++) {
            System.out.println("main com.hurricane.thread.thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = true;
        //唤醒sub
        this.notify();
    }
}
