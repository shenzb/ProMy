package com.hurricane.thread.data_structure.list;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenbiao.shen on 2016/6/23.
 */
public class ListMain {

    public static  final int TASK_COUNT=10000;


    public static final int MAX_THREADS=2000;

    public static void main(String[] args) {

        List list=new CopyOnWriteArrayList<String>();
        List listvetor=new Vector();
        CounterPoolExecutor exe=new CounterPoolExecutor(MAX_THREADS,MAX_THREADS,60L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());

        long starttime=System.currentTimeMillis();
        exe.startTime=starttime;

        exe.funcname="testGetCopyOnWriteList";

//        for(int i=0;i<TASK_COUNT;i++){
//            exe.submit(new AccessListThread("thread"+i,list));
//        }

        for(int i=0;i<TASK_COUNT;i++){
            exe.submit(new AccessListThread("thread"+i,listvetor));
        }
    }

}
