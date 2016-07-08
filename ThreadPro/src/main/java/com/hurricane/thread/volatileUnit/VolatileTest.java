package com.hurricane.thread.volatileUnit;


/**
 * Created by zhenbiao.shen on 2016/6/27.
 *
 * volatile  将迫使所有线程均读写主内存中的对应变量，从而使得volatile 变量在多线程间可见。 此时tryExit（） 总是设法去主内存区获取数据，当它获取等式左边的数据后，，偶去等式右边的数据前，swap 线程极有可能已经修改了isExit的值，故该表达式isExit==！isExit 很有可能相等。
 *
 * 而 不加volatile 修饰读取的是线程工作区中变量的副本，读取的值基本上是一样的。
 *
 *
 * 但是在本地测试 两种情况基本一致。
 */
public class VolatileTest {

    volatile boolean isExit;

    long startTime;

    public void tryExit() {
        if (isExit == !isExit) {
            System.out.println("退出"+(System.currentTimeMillis()-startTime));
            System.exit(0);
        }
    }

    public VolatileTest(long startTime) {
        this.startTime = startTime;
    }

    public void swapValue(){
        isExit=!isExit;
    }

    public static void main(String[] args) {


        try {
            long start =System.currentTimeMillis();
            final VolatileTest volatileTest=new VolatileTest(start);

            Thread mainThread=new Thread(){
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
                    System.out.println("mainThread start");
                    int count=0;
                    while (true){
                        count++;
                        System.out.println("count"+count);
                        volatileTest.tryExit();
                    }
                }
            };


            mainThread.start();


            Thread swapThread=new Thread(){
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
                    System.out.println("swapThread start");
                    while(true){
                        volatileTest.swapValue();
                    }
                }
            };
            swapThread.start();
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
