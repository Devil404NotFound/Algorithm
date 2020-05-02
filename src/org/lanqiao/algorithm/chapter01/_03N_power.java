package org.lanqiao.algorithm.chapter01;

/**
 * 【判断一个数是不是2的N次方】
 */
public class _03N_power {
    public static void main(String[] args) {
        int n = 32;
        boolean flag = false;
        if(((n-1)&n) == 0) {
            flag = true;
        }
        System.out.println(flag);
    }
}
