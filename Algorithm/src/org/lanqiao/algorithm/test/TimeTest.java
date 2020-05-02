package org.lanqiao.algorithm.test;

import java.util.ArrayList;
import java.util.List;

public class TimeTest {

    public static void main(String[] args) {
        long begin, end,time;
        /*begin = System.currentTimeMillis();
        //里添加测试代码（方法等）

        end = System.currentTimeMillis();
        System.out.println(end - begin + "ms");*/
        int n = 30000000;
        int test;
        int[] arr = arrTest(n);
        List<Integer> list = listTest(n);
        begin = System.currentTimeMillis();
        /**
         * 这里添加测试代码（方法等）
         */
        int temp;
        for (int i = 0; i < n; i++) {
            temp = list.get(i);
            test = temp;
            test = temp * 2;
            test = temp * 3;
            test = temp * 4;
        }
        end = System.currentTimeMillis();
        System.out.println(end - begin + "ms");
        begin = System.currentTimeMillis();
        //里添加测试代码（方法等）
        for (int i = 0; i < n; i++) {
            test = list.get(i);
            test = list.get(i) * 2;
            test = list.get(i) * 3;
            test = list.get(i) * 4;
        }
        end = System.currentTimeMillis();
        System.out.println(end - begin + "ms");
    }
    public static int[] arrTest(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n;
        }
        return arr;
    }
    public static List<Integer> listTest(int n){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }
}
