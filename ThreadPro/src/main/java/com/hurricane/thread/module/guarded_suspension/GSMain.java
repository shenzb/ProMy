package com.hurricane.thread.module.guarded_suspension;

/**
 * Created by zhenbiao.shen on 2016/6/20.
 */
public class GSMain {

    public static void main(String[] args) {
        RequestQueue requestQueue=new RequestQueue();
        for(int  i=0;i<10;i++){
            new ServerThread(requestQueue,"ServerThread"+i).start();
        }
        for(int i=0;i<10;i++){
             new ClientThread(requestQueue,"ClientThread"+i).start();
        }

    }

}
