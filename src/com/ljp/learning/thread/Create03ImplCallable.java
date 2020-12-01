package com.ljp.learning.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**创建线程的第3种方式——实现Callable接口
 * @author ljp
 * @date 2020/10/27 13:28
 */
public class Create03ImplCallable implements Callable<String> {
    public static int count = 200;
    @Override
    public String call() throws Exception {
        while(count > 0){
            System.out.println(Thread.currentThread().getName() + "---" + --count);
        }
        return "over";
    }

    public static void main(String[] args) {
        Callable<String> callable = new Create03ImplCallable();
        FutureTask<String> futureTask1 = new FutureTask<>(callable);
        FutureTask<String> futureTask2 = new FutureTask<>(callable);
        FutureTask<String> futureTask3 = new FutureTask<>(callable);
        Thread myThread1 = new Thread(futureTask1);
        Thread myThread2 = new Thread(futureTask2);
        Thread myThread3 = new Thread(futureTask3);
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}
