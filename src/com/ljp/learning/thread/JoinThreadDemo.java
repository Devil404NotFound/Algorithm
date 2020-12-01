package com.ljp.learning.thread;

/** //保证线程t1, t2,t3的执行顺序（先t1再t2最后t3）
 * @author ljp
 * @date 2020/11/4 16:50
 */
public class JoinThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("t1");
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2");
            }
        };
        Thread t3 = new Thread(){
            @Override
            public void run() {
                try{
                    t2.join();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("t3");
            }
        };
        t3.start();
//        Thread.sleep(1000);
        t2.start();
        t1.start();
//        t2.interrupt();
    }
}
