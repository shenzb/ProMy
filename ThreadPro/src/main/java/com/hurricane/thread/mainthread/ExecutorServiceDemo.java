package com.hurricane.thread.mainthread;

import com.hurricane.thread.pojo.MyCallable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zhenbiao.shen on 2016/2/4.
 *
 * ExecutorService、Callable、Future这个对象实际上都是属于Executor框架中的功能类。想要详细了解Executor框架的可以访问http://www.javaeye.com/topic/366591 ，
 * 这里面对该框架做了很详细的解释。返回结果的线程是在JDK1.5中引入的新特征。
 * 可返回值的任务必须实现Callable接口，类似的，无返回值的任务必须Runnable接口。执行Callable任务后，
 * 可以获取一个Future的对象，在该对象上调用get就可以获取到Callable任务返回的Object了，再结合线程池接口ExecutorService就可以实现传说中有返回结果的多线程了。
 * 下面提供了一个完整的有返回结果的多线程测试例子
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List myCallables=new ArrayList<MyCallable>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
            myCallables.add(c);
            // System.out.println(">>>" + f.get().toString());
        }
        List<Future> list= pool.invokeAll(myCallables);
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
}
