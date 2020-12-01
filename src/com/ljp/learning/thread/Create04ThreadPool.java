package com.ljp.learning.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ljp
 * @date 2020/10/27 13:47
 */
public class Create04ThreadPool {
    public static void main(String[] args) {
        ExecutorService es;
//        es = Executors.newFixedThreadPool(5);
//        es = Executors.newSingleThreadExecutor();
//        es = Executors.newCachedThreadPool();
        /**
         * corePoolSize:核心线程（当开启线程到核心线程后，就会一直保持核心线程数
         * maximumPoolSize：最大线程：当线程达到最大线程时，其他线程只能在线程队列等待
         * keepAliveTime：核心线程外最大线程之内的线程存活时间，下一个参数为时间单位（时、分、秒等）
         * 保活时间的单位
         * 线程队列
         */
        es = new ThreadPoolExecutor(2,10, 10, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 3; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + "---" + j);
                    }
                }
            });
        }
    }
}
