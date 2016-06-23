package com.hurricane.thread.module.Product_consumer;

/**
 * Created by zhenbiao.shen on 2016/6/20.
 */
public class PCData {

    private int count;

    public PCData(int count) {
        this.count = count;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "count=" + count +
                '}';
    }
}
