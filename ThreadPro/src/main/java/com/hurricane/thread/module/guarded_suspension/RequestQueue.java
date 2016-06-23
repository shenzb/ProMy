package com.hurricane.thread.module.guarded_suspension;

import java.util.LinkedList;

/**
 * Created by zhenbiao.shen on 2016/6/20.
 */
public class RequestQueue {

    private LinkedList queue = new LinkedList();

    synchronized public Request getRequest() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (Request) queue.poll();
    }

    synchronized public void addRequest(Request request) {
        queue.add(request);
        notifyAll();
    }
}
