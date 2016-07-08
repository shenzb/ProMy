package com.hurricane.thread.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhenbiao.shen on 2016/6/28.
 */
public class DeadLockCar extends Thread {

    protected Object myDirect;

    static ReentrantLock east=new ReentrantLock(false);
    static ReentrantLock west=new ReentrantLock(false);
    static ReentrantLock north=new ReentrantLock(false);
    static ReentrantLock south=new ReentrantLock(false);


    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public DeadLockCar(Object myDirect) {
        this.myDirect = myDirect;
        if(myDirect==south){
            this.setName("south");
        } if(myDirect==north){
            this.setName("north");
        } if(myDirect==west){
            this.setName("west");
        } if(myDirect==east){
            this.setName("east");
        }
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p/>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        if(myDirect==south){
            try {
                west.lockInterruptibly();
                System.out.println("获取向西的锁");
                Thread.sleep(1000);
                System.out.println("尝试获取向南的锁……");
                south.lockInterruptibly();
                System.out.println("car to south has passed");
            } catch (InterruptedException e) {
                System.out.println("car to south is killed");
            } finally {
                if(west.isHeldByCurrentThread()){
                    System.out.println("释放向西的锁");
                    west.unlock();
                }
                if(south.isHeldByCurrentThread()){
                    System.out.println("释放向南的锁");
                    south.unlock();
                }

            }
        }
        if(myDirect==north){
            try {
                east.lockInterruptibly();
                System.out.println("获取向东的锁");
                Thread.sleep(1000);
                System.out.println("尝试获取向北的锁……");
                north.lockInterruptibly();
                System.out.println("car to north has passed");
            } catch (InterruptedException e) {
                System.out.println("car to north is killed");
            } finally {
                if(east.isHeldByCurrentThread()){
                    System.out.println("释放向东的锁");
                    east.unlock();
                }
                if(north.isHeldByCurrentThread()){
                    System.out.println("释放向北的锁");
                    north.unlock();
                }

            }
        }
        if(myDirect==west){
            try {
                north.lockInterruptibly();
                System.out.println("获取向北的锁");
                Thread.sleep(1000);
                System.out.println("尝试获取向西的锁……");
                west.lockInterruptibly();
                System.out.println("car to west has passed");
            } catch (InterruptedException e) {
                System.out.println("car to west is killed");
            } finally {
                if(north.isHeldByCurrentThread()){
                    System.out.println("释放向北的锁");
                    north.unlock();
                }
                if(west.isHeldByCurrentThread()){
                    System.out.println("释放向西的锁");
                    west.unlock();
                }

            }
        }

        if(myDirect==east){
            try {
                south.lockInterruptibly();
                System.out.println("获取向南的锁");
                Thread.sleep(1000);
                System.out.println("尝试获取向东的锁……");
                east.lockInterruptibly();
                System.out.println("car to east has passed");
            } catch (InterruptedException e) {
                System.out.println("car to east is killed");
            } finally {
                if(south.isHeldByCurrentThread()){
                    System.out.println("释放向南的锁");
                    south.unlock();
                }
                if(east.isHeldByCurrentThread()){
                    System.out.println("释放向东的锁");
                    east.unlock();
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLockCar carSouth=new DeadLockCar(south);
        DeadLockCar carnorth=new DeadLockCar(north);
        DeadLockCar carwest=new DeadLockCar(west);
        DeadLockCar careast=new DeadLockCar(east);
        carSouth.start();
        carnorth.start();
        carwest.start();
        careast.start();
        Thread.sleep(10000);
        carnorth.interrupt();
    }
}
