package com.ljp.learning.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**创建线程的第二种方式——实现Runnable接口
 * @author ljp
 * @date 2020/10/27 13:12
 */
public class Create02ImplRunnable implements Runnable{
    static AtomicInteger count = new AtomicInteger(20);
    @Override
    public void run() {
        while(count.get() > 0){
            System.out.println(Thread.currentThread().getName() + "剩余票数" + count.addAndGet(-1));
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Create02ImplRunnable();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
