package com.hurricane.thread.module.Product_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zhenbiao.shen on 2016/6/21.
 */
public class PCMain {
    public static void main(String[] args) {
        try {
            BlockingQueue<PCData> blockingQueue=new LinkedBlockingDeque<PCData>(10);

            Producer producer1=new Producer(blockingQueue);
            Producer producer2=new Producer(blockingQueue);
            Producer producer3=new Producer(blockingQueue);
            Producer producer4=new Producer(blockingQueue);
            Producer producer5=new Producer(blockingQueue);

            Consumer consumer1=new Consumer(blockingQueue);
            Consumer consumer2=new Consumer(blockingQueue);
            Consumer consumer3=new Consumer(blockingQueue);
            Consumer consumer4=new Consumer(blockingQueue);


            ExecutorService service= Executors.newCachedThreadPool();
            service.execute(producer1);
            service.execute(producer2);
            service.execute(producer3);
            service.execute(producer4);
            service.execute(producer5);
            service.execute(consumer1);
            service.execute(consumer2);
            service.execute(consumer3);
            service.execute(consumer4);

            Thread.sleep(10000);
            producer1.stop();
            producer2.stop();
            producer2.stop();
            producer3.stop();
            producer4.stop();
            producer5.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
