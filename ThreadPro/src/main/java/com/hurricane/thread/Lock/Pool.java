package com.hurricane.thread.Lock;

import java.util.concurrent.Semaphore;

/**
 * Created by zhenbiao.shen on 2016/6/27.
 *
 * semaphore
 */
public class Pool {

    /**
     * 最大允许100个许可
     */
    private static final int MAX_AVALABLE=100;
    /**
     * 用于标识池中的项是否正在被使用
     */
    private boolean[] used=new boolean[MAX_AVALABLE];


    private Object[] items=new Object[MAX_AVALABLE];
    /**
     * 最大允许100个许可
     */
    private Semaphore semaphore=new Semaphore(MAX_AVALABLE,true);


    public Object getItem() throws InterruptedException {
        semaphore.acquire();
        return getNextItem();
    }

    private void putItem(Object object){
        if(markUnuse(object)){
            semaphore.release();
        }

    }

    protected synchronized Object getNextItem(){
        for(int i=0;i<MAX_AVALABLE;i++){
            if(!used[i]){
                used[i]=true;
              return   items[i];
            }
        }
        return null;
    }

    protected synchronized boolean markUnuse(Object object){
        for(int i=0;i<MAX_AVALABLE;i++){
            if(items[i]==object){
                if(used[i]){
                    used[i]=false;
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

}
