package org.lanqiao.algorithm.sort;

import org.lanqiao.Utils.Util;

import java.util.ArrayList;

public class _10RadixSort {
    private static ArrayList[] bucket = new ArrayList[10];
    static{
        for (int i = 0; i < 10; i++) {
            bucket[i] = new ArrayList<Integer>();
        }
    }
    public static void main(String[] args) {
        int[] arr = Util.getRandomArr(10, 0,100);
        Util.print(arr);
        radixSort(arr);
        Util.print(arr);
    }
    public static void radixSort(int[] arr){
        int maxDigit = 0;
        //求数组最大值
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        //求最大位数
        while(max != 0){
            maxDigit++;
            max /= 10;
        }
        //对数组的个十百千...都做一次入桶出桶
        for (int i = 1; i <= maxDigit; i++) {
            radixSort(arr, i);
        }
    }
    public static void radixSort(int[] arr, int d){
        //入桶
        int k;
        for (int i = 0; i < arr.length; i++) {
            k = Util.getDigit(arr[i], d);
            bucket[k].add(arr[i]);
        }
        //出桶
        int current = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (Object num: bucket[i]) {
                arr[current++] = (Integer)num;
            }
        }
        //清桶
        clearAll(bucket);
    }
    public static void clearAll(ArrayList[] buckut){
        for (int i = 0; i < buckut.length; i++) {
            buckut[i].clear();
        }
    }
}
