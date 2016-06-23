package com.hurricane.thread.module.guarded_suspension;

import com.google.common.collect.Lists;
import com.hurricane.thread.module.FutureModle.FutureData;

import java.util.List;

/**
 * Created by zhenbiao.shen on 2016/6/20.
 */
public class ClientThread extends Thread {
    private RequestQueue requestQueue;

    private List<Request> requestList= Lists.newArrayList();

    public ClientThread(RequestQueue requestQueue,String name){
        super(name);
        this.requestQueue=requestQueue;
    }


    public  void run(){
        for(int i=0;i<10;i++){
            Request request=new Request("RequestId:"+i+" Thread_Name:"+Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName()+" reqeusts "+ request);
            FutureData futureData=new FutureData();
            request.setData(futureData);
            requestList.add(request);
            requestQueue.addRequest(request);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ClientThread Name is:"+Thread.currentThread().getName());
        }

        for(Request<FutureData> request:requestList){
            System.out.println("clientThread name is :"+Thread.currentThread().getName()+" Reponse is :"+request.getData().getResult());
        }
        System.out.println(Thread.currentThread().getName()+"request end");
    }


    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }
}
