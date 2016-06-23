package com.hurricane.thread.pojo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhenbiao.shen on 2016/2/4.
 *
 *  使当前线程（即调用该方法的线程）暂停执行一段时间，让其他线程有机会继续执行，但它并不释放对象锁。也就是说如果有synchronized同步快，其他线程仍然不能访问共享数据。注意该方法要捕捉异常。
 *  例如有两个线程同时执行(没有synchronized)一个线程优先级为MAX_PRIORITY，另一个为MIN_PRIORITY，如果没有Sleep()方法，只有高优先级的线程执行完毕后，低优先级的线程才能够执行；
 *  但是高优先级的线程sleep(500)后，低优先级就有机会执行了。总之，sleep()可以使低优先级的线程得到执行的机会，当然也可以让同优先级、高优先级的线程有执行的机会。
 */
public class Data {
    public int num = 0;
    Lock lock = new ReentrantLock();

    /**
     * 将同步代码改用lock方式
     *
     * @param threadName
     * @return
     */
    public int getEven(String threadName) {
        lock.lock();
        try {
            System.out.println("当前线程：" + threadName + " start");
            ++num;
            Thread.sleep(1000l);//让另外线程先执行,加大测试效果几率
            ++num;
            System.out.println("当前线程：" + threadName + " end");
            return num;//!!!!!!!!!!!!!!!!注意这里如果将return放到finally之后，则可能会有线程在return 返回之前进入修改num的值，导致错误。
        } catch (InterruptedException e) {
            e.printStackTrace();
            return num;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 将同步代码改用synchronized 方法锁的方式
     *
     * @param threadName
     * @return
     */
    public synchronized int getSyncEven(String threadName) {

        try {
            System.out.println("当前线程：" + threadName + " start");
            ++num;
            Thread.sleep(1000l);//让另外线程先执行,加大测试效果几率
            ++num;
            System.out.println("当前线程：" + threadName + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 将同步代码改用synchronized 对象锁的方式
     *
     * @param threadName
     * @return
     */
    public int getSyncEvenInner(String threadName) {
        synchronized (this) {
            try {

                System.out.println("当前线程：" + threadName + " start");
                ++num;
                Thread.sleep(1000l);//让另外线程先执行,加大测试效果几率
                ++num;
                System.out.println("当前线程：" + threadName + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return num;
        }
    }
}
