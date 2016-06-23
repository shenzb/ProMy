package com.hurricane.thread.module.FutureModle;

/**
 * Created by zhenbiao.shen on 2016/6/15.
 */
public class FutureModleMain {
    public static void main(String[] args) {
        Client client=new Client();
        long start=System.currentTimeMillis();
        Data data=client.request("xixi");
        String str=data.getResult();
        System.out.println("result:"+str);
        String st2r=data.getResult();
        System.out.println("result:"+st2r);
        System.out.println("执行时间"+(System.currentTimeMillis()-start));

    }
}
