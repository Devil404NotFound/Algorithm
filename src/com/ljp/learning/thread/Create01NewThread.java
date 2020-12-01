package com.ljp.learning.thread;

/**
 * 创建线程的第1种方式——继承Thread类
 * @author ljp
 * @date 2020/10/27 13:06
 */
public class Create01NewThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }

    public static void main(String[] args) {
        Thread myThread1 = new Create01NewThread();
        Thread myThread2 = new Create01NewThread();
        Thread myThread3 = new Create01NewThread();
        Thread myThread4 = new Create01NewThread();
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
    }
}
