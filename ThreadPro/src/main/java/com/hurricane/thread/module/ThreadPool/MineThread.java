package com.hurricane.thread.module.ThreadPool;

/**
 * Created by zhenbiao.shen on 2016/6/22.
 */
public class MineThread implements Runnable {
//    private Logger logger= LoggerFactory.getLogger(MineThread.class);

    private String name;


    public MineThread(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        try {
            Thread.sleep(1);
            System.out.println(name);
//            logger.info(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MineThread{" +
                "name='" + name + '\'' +
                '}';
    }
}
