package org.lanqiao.algorithm.chapter06;

import org.lanqiao.Utils.Util;

/**
 * 前K个数
 * 求海量数据（正整数）按逆序排列的前K个数（topL），因为数据量太大，不能全部存储在内存中，只能一个一个地从磁盘或者网络上读取数据，这个问题
 */

public class _03小顶堆与topK问题 {
    public static void main(String[] args){
        int[] arr = Util.getRandomArr(30,0,30);
        int[] res = topK(arr,10);
        Util.print(arr);
        org.lanqiao.algorithm.sort.QuickSort.quickSort(arr);
        Util.print(arr);
        Util.print(res);

    }
    public static int[] topK(int[] arr, int k){
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        for (int i = (res.length>>1) - 1; i >= 0; i--){
            minHeap(res,i,res.length);
        }
        for (int i = k; i < arr.length; i++) {
            if(arr[i] > res[0]){
                res[0] = arr[i];
                minHeap(res,0, res.length);
            }
        }
        return res;
    }
    public static void minHeap(int[] arr,int i, int length){
        int j = 2 * i + 1;
        int temp = arr[i];
        while(j < length){
            if(j + 1 < length && arr[j+1] < arr[j]){
                j++;
            }
            if(arr[j] < temp){
                arr[i] = arr[j];
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
}
