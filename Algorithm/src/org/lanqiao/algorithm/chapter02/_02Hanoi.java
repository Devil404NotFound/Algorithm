package org.lanqiao.algorithm.chapter02;

public class _02Hanoi {
    private static int num;
    public static void main(String[] args) {
        hanoi_fun(3, 'A', 'B','C');

    }

    /**
     * 将第n个盘从A通过C移动到B
     * @param n
     * @param a
     * @param b
     * @param c
     */
    public static void hanoi_fun(int n, char a, char b, char c){
        if(n == 1){
            System.out.println( ++num +"#：" + n + " from " + a + " to " + b);
            return;
        }
        hanoi_fun(n-1, a, c, b);
        System.out.println(++num + "#：" + n + " from " + a + " to " + b);
        hanoi_fun(n-1, c, b, a);
    }
}
