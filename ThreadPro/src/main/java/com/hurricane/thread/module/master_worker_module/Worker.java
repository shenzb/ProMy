package com.hurricane.thread.module.master_worker_module;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by zhenbiao.shen on 2016/6/15.
 */
public class Worker implements Runnable {

    private Queue<Object> taskQueue = new ConcurrentLinkedDeque<Object>();

    private Map<String, Object> resultMap = Maps.newConcurrentMap();


    public Queue<Object> getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(Queue<Object> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void run() {


        while (true) {

            Object object = taskQueue.poll();
            if (object == null) {

                break;
            } else {
                Object result = handle(object);
                resultMap.put(Integer.toString(result.hashCode()), result);
            }

        }

    }

    public Object handle(Object object) {
        return object;
    }
}
