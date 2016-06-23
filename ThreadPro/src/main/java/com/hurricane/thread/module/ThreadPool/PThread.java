package com.hurricane.thread.module.ThreadPool;

/**
 * Created by zhenbiao.shen on 2016/6/22.
 */
public class PThread extends Thread {

    private ThreadPool threadPool;

    private Runnable target;

    private boolean isShutDown;

    private boolean isIdle;

    public PThread(ThreadPool threadPool, Runnable target, String name) {
        super(name);
        this.threadPool = threadPool;
        this.target = target;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public Runnable getTarget(){
        return target;
    }

    public synchronized void setTarget(Runnable target) {
        this.target = target;
        notifyAll();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if(target!=null){
                    target.run();
                    threadPool.rePool(this);
                    synchronized (this) {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void shutDown(){
        isShutDown=true;
        notifyAll();
    }
}
