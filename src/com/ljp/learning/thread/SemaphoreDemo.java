package com.ljp.learning.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author ljp
 * @date 2020/12/2 12:46
Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
以下代码模拟了对某个服务的并发请求，每次只能有 3 个客户端同时访问，请求总数为 10
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        final int clientCount = 3; //客户端数量
        final int totalRequestCount = 10; //请求总数
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            es.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.print(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {

                }finally{
                    semaphore.release();
                }
            });
        }
        es.shutdown();
    }
}
