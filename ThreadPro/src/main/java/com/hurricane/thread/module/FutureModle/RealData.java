package com.hurricane.thread.module.FutureModle;

/**
 * Created by zhenbiao.shen on 2016/6/15.
 */
public class RealData implements Data {

    protected String data = "";

    public RealData(String request) {
        try {
            System.out.println("我在努力的获取结果……");
            Thread.sleep(1);
            StringBuffer sb = new StringBuffer("====================");
            sb.append(request);

            this.data = sb.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {

        return data;
    }

    @Override
    public String toString() {
        return "RealData{" +
                "data='" + data + '\'' +
                '}';
    }
}
