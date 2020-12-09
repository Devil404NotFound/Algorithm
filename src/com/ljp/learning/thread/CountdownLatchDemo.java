package com.ljp.learning.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ljp
 * @date 2020/12/2 12:31
 */
public class CountdownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            es.execute(() -> {
                System.out.print("run.. ");
               countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.print(" end ");
        es.shutdown();
        /*
            输出：
                run.. run.. run.. run.. run.. run.. run.. run.. run.. run..  end
         */
    }
}
