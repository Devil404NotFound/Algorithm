package com.ljp.learning.thread;

/**
 * @author ljp
 * @date 2020/11/4 15:42
 */
public class InterruptThreadDemo {
    public static void main(String[] args) throws InterruptedException{
        InterruptThread interruptThread = new InterruptThread();
        System.out.println("Starting thread...");
        interruptThread.start();
        Thread.sleep(3000);
        System.out.println("Interrupt thread...:" + interruptThread.getName());
        interruptThread.stop = true;
        interruptThread.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }
}
class InterruptThread extends Thread{
    volatile boolean stop = false;
    @Override
    public void run() {
        while(!stop){
            System.out.println(Thread.currentThread().getName() + " is running......");
            try{
                sleep(1000);
            }catch (InterruptedException e){
                System.out.println("week up from block...");
                stop = true;
            }
        }
    }
}
