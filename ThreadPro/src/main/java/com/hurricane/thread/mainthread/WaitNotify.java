package com.hurricane.thread.mainthread;

import com.hurricane.thread.pojo.Business;

/**
 * Created by zhenbiao.shen on 2016/2/4.
 *  典型案例：子线程执行10次，主线程执行100次，两者交替50次。
 *  wait()和notify()、notifyAll()
 *  这三个方法用于协调多个线程对共享数据的存取，所以必须在synchronized语句块内使用。synchronized关键字用于保护共享数据，阻止其他线程对共享数据的存取，
 *  但是这样程序的流程就很不灵活了，如何才能在当前线程还没退出synchronized数据块时让其他线程也有机会访问共享数据呢？此时就用这三个方法来灵活控制。
 *  wait()方法使当前线程暂停执行并释放对象锁标示，让其他线程可以进入synchronized数据块，当前线程被放入对象等待池中。当调用notify()方法后，
 *  将从对象的等待池中移走一个任意的线程并放到锁标志等待池中，只有锁标志等待池中线程能够获取锁标志；如果锁标志等待池中没有线程，则notify()不起作用。
 *  notifyAll()则从对象等待池中移走所有等待那个对象的线程并放到锁标志等待池中。
 */
public class WaitNotify {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(
                new Runnable() {
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            business.sub(i);
                        }
                    }
                }
        ).start();

        for (int i = 1; i <= 10; i++) {
            business.main(i);
        }
    }
}
