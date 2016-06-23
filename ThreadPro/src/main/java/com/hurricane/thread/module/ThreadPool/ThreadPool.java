package com.hurricane.thread.module.ThreadPool;


import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Vector;

/**
 * Created by zhenbiao.shen on 2016/6/22.
 */
public class ThreadPool {

    private static ThreadPool threadPool;

    private List<PThread> idleThread;

    private int threadCount;

    private boolean isShutDown;

    private static  int count=0;

    public synchronized static ThreadPool getInstance(){
        if(threadPool==null){
            threadPool=new ThreadPool();
        }
        return threadPool;
    }

    public ThreadPool() {
        this.idleThread = new Vector<PThread>(5);
        threadCount=0;
    }

    public int getThreadCount() {
        return threadCount;
    }

    /**
     * 添加空闲线程
     * @param pThread
     */
    public synchronized void rePool(PThread pThread){

       if(!isShutDown){
           idleThread.add(pThread);
       }else{
           pThread.shutDown();
       }
    }

    public void shutDownAllThread(){
        isShutDown=true;
        threadCount=0;
        if(CollectionUtils.isNotEmpty(idleThread)){
            for(PThread pThread:idleThread){
                pThread.shutDown();
            }
        }
    }

    public synchronized void  start(Runnable target){
        PThread pThread=null;
        if(!isShutDown){

            if(CollectionUtils.isNotEmpty(idleThread)){
                int lastIndex=idleThread.size()-1;
                pThread=idleThread.get(lastIndex);
                idleThread.remove(lastIndex);
                pThread.setTarget(target);
//                System.out.println("空闲线程："+target);
            }else{
//                System.out.println(target);
                pThread=new PThread(threadPool,target,"Pthread"+threadCount);
                pThread.start();
                threadCount++;
            }
        }
    }
}
