package com.hurricane.thread.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhenbiao.shen on 2016/6/27.
 */
public class ReenTrantLockUnit {
    ReentrantLock lock=new ReentrantLock();

    private Runnable createTask(){
        return new Runnable() {
            public void run() {

                while(true){
                    try {
//                        if(lock.tryLock()){
                            try {
                                lock.lockInterruptibly();
                                System.out.println("locked "+Thread.currentThread().getName());
                                Thread.sleep(1000);

                                break;

                            } catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                if(lock.isHeldByCurrentThread()){
                                    System.out.println("unlock "+Thread.currentThread().getName());
                                    lock.unlock();
                                }
                            }

//                        }else{
//                            System.out.println("unlock to lock "+Thread.currentThread().getName());
//                        }
                    } catch (Exception e) {
                        System.out.println(Thread.currentThread().getName()+" InterruptedException");
                    }
                }
            }
        };
    }


    public static void main(String[] args) {
        try {
            ReenTrantLockUnit reenTrantLockUnit=new ReenTrantLockUnit();
            Thread first=new Thread(reenTrantLockUnit.createTask(),"firstTast");
            Thread second=new Thread(reenTrantLockUnit.createTask(),"secondTask");
            first.start();
            second.start();
            Thread.sleep(600);
            second.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
