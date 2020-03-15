package org.lanqiao.algorithm.chapter01;

/**【二进制中1的个数】
 * 请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。
 * 例：9的二进制表示为10001，有2位是1
 */
public class _02CountBinary {
    public static void main(String[] args) {
        int n = 9;
        System.out.println(Integer.toBinaryString(n));
        int count = funcition1(n);
        System.out.println(count);
        count = function2(n);
        System.out.println(count);
        count = function3(n);
        System.out.println(count);
    }

    //方法一
    private static int funcition1(int n) {
        int count = 0;
        while(n != 0) {
            if((n & 1) == 1){
                count++;
            }
            n = n >> 1;
        }
        return count;
    }
    //方法二
    public static int function2(int n){
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if((n&(1<<i))==(1<<i)){
                count++;
            }
        }
        return count;
    }
    //方法三
    public static int function3(int n){
        int count = 0;
        while(n != 0){
            n = (n-1)&n;
            count++;
        }
        return count;
    }
}
