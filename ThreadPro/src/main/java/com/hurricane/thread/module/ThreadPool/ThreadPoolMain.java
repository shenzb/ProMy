package com.hurricane.thread.module.ThreadPool;

import java.text.MessageFormat;

/**
 * Created by zhenbiao.shen on 2016/6/22.
 */
public class ThreadPoolMain {

    public static void main(String[] args) {

//        long start=System.currentTimeMillis();
//        for(int i=0;i<10000;i++){
//            new Thread(new MineThread(MessageFormat.format("mythread {0}",i))).start();
//        }
//        System.out.println(MessageFormat.format("不用线程池消耗时间：{0}",System.currentTimeMillis()-start));


        long start2=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            ThreadPool.getInstance().start(new MineThread(MessageFormat.format("mythread {0}",i)));
        }
//        System.out.println(MessageFormat.format("使用线程池消耗时间：{0}",System.currentTimeMillis()-start2));

//        long start3=System.currentTimeMillis();
//        ExecutorService executorService= Executors.newCachedThreadPool();
//        for(int i=0;i<10000;i++){
//            executorService.execute(new MineThread(MessageFormat.format("mythread {0}",i)));
//        }
//        System.out.println(MessageFormat.format("使用线程池消耗时间：{0}",System.currentTimeMillis()-start3));
    }
}
