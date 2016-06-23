package com.hurricane.thread.module.Product_consumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhenbiao.shen on 2016/6/20.
 */
public class Producer implements Runnable {


    private volatile boolean isRunning=true;

    private BlockingQueue<PCData> blockingQueue;

    private static AtomicInteger count=new AtomicInteger();

    private static final int SLEEPTIME=1000;

    public Producer(BlockingQueue<PCData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        PCData pcData=null;
        Random random=new Random();
        System.out.println("start producer id="+Thread.currentThread().getId());
        try {
            while (isRunning){
                Thread.sleep(random.nextInt(SLEEPTIME));
                pcData=new PCData(count.incrementAndGet());
                System.out.println(pcData +" is put into queue");
                if(!blockingQueue.offer(pcData,2, TimeUnit.SECONDS)){
                    System.out.println(MessageFormat.format("fail to put data {0}",pcData));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        isRunning=false;
    }
}
