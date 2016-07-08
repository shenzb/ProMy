package com.hurricane.thread.module.ThreadPool;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by zhenbiao.shen on 2016/6/22.
 */
public class MineThread implements Runnable,Comparable {
//    private Logger logger= LoggerFactory.getLogger(MineThread.class);

    private String name;


    public MineThread(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(name);
//            logger.info(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MineThread{" +
                "name='" + name + '\'' +
                '}';
    }

    public int compareTo(Object o) {
        MineThread otherThread =(MineThread)o;
        List<String> me= Lists.newArrayList(Splitter.on("_").omitEmptyStrings().split(this.name).iterator());
        List<String> othere= Lists.newArrayList(Splitter.on("_").omitEmptyStrings().split(otherThread.name).iterator());
        if(Integer.valueOf(me.get(1))>Integer.valueOf(othere.get(1))){
            return 1;
        }
        else if(Integer.valueOf(me.get(1))<Integer.valueOf(othere.get(1))){
            return -1;
        }
        return 0;
    }
}
