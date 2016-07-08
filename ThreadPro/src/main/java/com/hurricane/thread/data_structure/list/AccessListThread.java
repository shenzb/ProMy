package com.hurricane.thread.data_structure.list;

import java.util.List;
import java.util.Random;

/**
 * Created by zhenbiao.shen on 2016/6/23.
 */
public class AccessListThread implements Runnable {

    protected String name;

    private List list;

    private Random random = new Random();


    public AccessListThread(String name, List list) {
        this.name = name;
        this.list = list;
    }
    

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        for(int i=0;i<500;i++){
            try {
                getList(random.nextInt(1000));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getList(int index) {
        return list.get(index);
    }
}
