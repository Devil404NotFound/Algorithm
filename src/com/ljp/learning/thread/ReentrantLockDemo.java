package com.ljp.learning.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ljp
 * @date 2020/12/1 23:00
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        LockExample lockExample = new LockExample();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(() -> lockExample.fun());
        es.execute(() -> lockExample.fun());
        es.shutdown();
    }
}
class LockExample {
    private Lock lock = new ReentrantLock();
    public void fun(){
        lock.lock();
        try{
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }finally{
            lock.unlock();//确保释放锁，从而避免发生死锁。
        }
    }
}
