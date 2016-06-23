package com.hurricane.thread.module.master_worker_module;

/**
 * Created by zhenbiao.shen on 2016/6/16.
 */
public class PlusWorker extends Worker {

    @Override
    public Object handle(Object object) {
        try {
            Long i = (Long) object;
            Thread.sleep(20);
            return i * i * i;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
