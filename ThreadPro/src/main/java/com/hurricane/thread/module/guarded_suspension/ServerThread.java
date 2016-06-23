package com.hurricane.thread.module.guarded_suspension;

import com.hurricane.thread.module.FutureModle.FutureData;
import com.hurricane.thread.module.FutureModle.RealData;

/**
 * Created by zhenbiao.shen on 2016/6/20.
 */
public class ServerThread extends Thread {

    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue,String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    public void run() {
        while(true){
            final Request request=requestQueue.getRequest();
            final FutureData futureData=(FutureData) request.getData();
            RealData realData=new RealData(request.getName());
            futureData.setRealData(realData);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" handles"+ request);
        }
    }
}
