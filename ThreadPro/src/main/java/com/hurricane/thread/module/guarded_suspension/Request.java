package com.hurricane.thread.module.guarded_suspension;

/**
 * Created by zhenbiao.shen on 2016/6/20.
 */
public class Request<T> {

    private T data;

    private String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    synchronized public T getData() {
        return data;
    }

    synchronized public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "data=" + data +
                '}';
    }
}
