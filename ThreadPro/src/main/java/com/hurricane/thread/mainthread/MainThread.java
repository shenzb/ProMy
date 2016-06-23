package com.hurricane.thread.mainthread;

import com.hurricane.thread.common.MyThread;
import com.hurricane.thread.pojo.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhenbiao.shen on 2016/2/4.
 * 读取方法保证数据是同步的偶数
 */
public class MainThread {

    public static void main(String[] args) {
        Data data = new Data();//多个线程操作的是同一个对象
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyThread(data));
        }
        executorService.shutdown();//关闭线程池，如果不关闭线程池将一直运行。
    }
}
