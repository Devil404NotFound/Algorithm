package com.ljp.learning.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ljp
 * @date 2020/12/4 18:50
 * 测试不安全的int并发和安全的AtomicInteger并发的结果
 */
//线程不安全例子
class ThreadUnsafeExample{
    private int cnt = 0;
    public void add() {
        cnt++;
    }
    public int getCnt(){
        return cnt;
    }
}
//使用AtomicInteger实现线程安全
class ThreadSafeExample {
    private AtomicInteger cnt = new AtomicInteger();
    public void add() {
        cnt.incrementAndGet();
    }
    public int getCnt() {
        return cnt.get();
    }
}
//使用synchronized关键字来实现线程安全
class ThreadSafeExample2 {
    private int cnt = 0;
    public synchronized void add() {
        cnt++;
    }
    public int getCnt(){
        return cnt;
    }
}
public class ThreadUnsafeDemo {
    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService es = Executors.newCachedThreadPool();
//        不安全的int
//        ThreadUnsafeExample example = new ThreadUnsafeExample();

//        安全的AtomicInteger
//        ThreadSafeExample example = new ThreadSafeExample();

        //同步关键字synchronized实现线程安全
        ThreadSafeExample2 example = new ThreadSafeExample2();
        for (int i = 0; i < threadSize; i++) {
            es.execute(() -> {
               example.add();
               countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(example.getCnt());
        es.shutdown();
    }
}
