package com.hurricane.thread.module.FutureModle;

/**
 * Created by zhenbiao.shen on 2016/6/15.
 */
public class Client  {

    public Data request(final String request){
        final FutureData futureData=new FutureData();
        new Thread(new Runnable() {
            public void run() {
                RealData realData=new RealData(request);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }

}
