package com.ljp.learning.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ljp
 * @date 2020/12/2 12:14
    wait() notify() notifyAll()调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，
    当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
    它们都属于 Object 的一部分，而不属于 Thread。
    只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
 */
class WaitNotifyExample{
    public synchronized void before(){
        System.out.println("before");
        notify();
    }
    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }
}
public class WaitNotifyDemo {
    public static void main(String[] args) {
        WaitNotifyExample example = new WaitNotifyExample();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(() -> example.after());
        es.execute(() -> example.before());
        es.shutdown();
        /*
        输出：
            before
            after
         */
    }
}
