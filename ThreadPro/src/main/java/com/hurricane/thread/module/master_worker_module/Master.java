package com.hurricane.thread.module.master_worker_module;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by zhenbiao.shen on 2016/6/15.
 */
public class Master {
    /**
     * 任务队列
     */
    protected Queue<Object> taskQueue = new ConcurrentLinkedDeque<Object>();
    /**
     * 子线程集合
     */
    protected Map<String, Thread> threadMap = Maps.newConcurrentMap();
    /**
     * 任务结果结合
     */
    protected Map<String, Object> resultMap = Maps.newConcurrentMap();

    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public Master(Worker worker, int workerCount) {
        worker.setResultMap(resultMap);
        worker.setTaskQueue(taskQueue);
        for (int i = 0; i < workerCount; i++) {
            threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
        }
    }

    public void submit(Object object) {
        taskQueue.add(object);
    }

    public void execute() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            Thread thread = entry.getValue();
            thread.start();
        }
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }
}
