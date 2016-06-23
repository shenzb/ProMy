package com.hurricane.thread.module.FutureModle;

/**
 * Created by zhenbiao.shen on 2016/6/15.
 */
public class FutureData implements Data {
    protected   RealData realData = null;

    boolean isReady = false;

    synchronized public void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notify();
//        System.out.println("设置数据完成…… isready "+isReady);
    }

    synchronized public String getResult() {
//        System.out.println("读取数据 isready "+isReady);
        if (!isReady) {
            try {
                wait();                             //一直等待，知道realData被注入
//                System.out.println("我在等待……");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("等待结束……");
        return realData.data;
    }

    @Override
    public String toString() {
        return "FutureData{" +
                "realData=" + realData +
                ", isReady=" + isReady +
                '}';
    }
}
