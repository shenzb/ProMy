package com.hurricane.thread.volatileUnit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhenbiao.shen on 2016/6/24.
 */
public class CounterMain extends Thread {

    public  static AtomicInteger count =new AtomicInteger(0);



    public  static   void inc() {

        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        count.incrementAndGet();
    }

    public static void main(String[] args) {

        try {
            //同时启动1000个线程，去进行i++计算，看看实际结果

            for (int i = 0; i < 1000; i++) {
                new Thread(new Runnable() {
                    public void run() {
                        CounterMain.inc();
                    }
                }).start();


            }
            Thread.sleep(3000);
            //这里每次运行的值都有可能不同,可能为1000
            System.out.println("运行结果:CounterMain.count=" + CounterMain.count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
