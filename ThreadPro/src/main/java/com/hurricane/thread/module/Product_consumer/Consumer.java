package com.hurricane.thread.module.Product_consumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhenbiao.shen on 2016/6/20.
 */
public class Consumer implements Runnable {

    private BlockingQueue<PCData> blockingQueue;

    private static  final int SLEEPTIME=1000;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {

        System.out.println("Start");
        while(true){
            try {
                Random random=new Random();
                PCData pcData=  blockingQueue.take();
                if(pcData!=null){
                    int count=pcData.getCount()*pcData.getCount();
                    System.out.println(MessageFormat.format("count1:{0},count2:{1},result:{2}",pcData.getCount(),pcData.getCount(),count));
                }
                Thread.sleep(random.nextInt(SLEEPTIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
