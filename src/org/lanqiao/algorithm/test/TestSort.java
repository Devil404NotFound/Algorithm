package org.lanqiao.algorithm.test;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.lanqiao.Utils.Util;
import org.lanqiao.algorithm.sort.*;
/**
 * 测试排序的性能
 * 性能：希尔排序 > 快速排序 > 插入排序 > 选择排序
 */

import java.util.Random;

public class TestSort {
    public static void main(String[] args) {
        long start, time;
        int N = 100000;
        Random random = new Random();
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(N - 1) + 1;
        }

        int[] arr5 = arr.clone();
        start = System.currentTimeMillis();
        QuickSort.quickSort2(arr5, 0, arr5.length - 1);
        time = System.currentTimeMillis() - start;
        System.out.println("快排2：" + time + "ms");

        int[] arr1 = arr.clone();
//        Util.print(arr1);
        start = System.currentTimeMillis();
        QuickSort.quickSort(arr1);
        time = System.currentTimeMillis() - start;
        //Util.print(arr1);
        System.out.println("快速排序：" + time + "ms");
        int[] arr2 = arr.clone();
        start = System.currentTimeMillis();
        InsertSort.insertSort(arr2);
        time = System.currentTimeMillis() - start;
        //Util.print(arr);
        //Util.print(arr2);
        System.out.println("插入排序：" + time + "ms");
        int[] arr3 = arr.clone();
        start = System.currentTimeMillis();
        SelectSort.selectSort(arr3);
        time = System.currentTimeMillis() - start;
        System.out.println("选择排序：" + time + "ms");
        int[] arr4 = arr.clone();
        start = System.currentTimeMillis();
        ShellSort.shellSort(arr4);
        time = System.currentTimeMillis() - start;
        System.out.println("希尔排序：" + time + "ms");


    }



}
