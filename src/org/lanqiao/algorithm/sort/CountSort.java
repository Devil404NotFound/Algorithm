package org.lanqiao.algorithm.sort;

import org.lanqiao.Utils.Util;

/**
 * 计数排序
 */
public class CountSort {
    public static void main(String[] args) {
        int[] arr = Util.getRandomArr(20,0,100);
        Util.print(arr);
        countSort(arr);
        Util.print(arr);
    }
    public static void countSort(int[] arr){
        //获取
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(max < arr[i]){
                max = arr[i];
            }
        }
        int[] helpArr = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            helpArr[arr[i]]++;
        }
       int current = 0;
        for (int i = 0; i < helpArr.length; i++) {
            while(helpArr[i] > 0) {
                arr[current++] = i;
                helpArr[i]--;
            }
        }
    }
}

