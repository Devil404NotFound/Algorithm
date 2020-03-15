package org.lanqiao.algorithm.sort;

import org.lanqiao.Utils.Util;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {5,9,8,3,6,4,1,10,0};
        int[] arr = {37,40,48,90,32,5,12,3,44,13};
        mergeSort(arr, 0, arr.length - 1);
        Util.print(arr);

    }
    public static void mergeSort(int[] arr, int low, int high){
        if(low < high){
            int mid = low + ((high - low) >> 1);
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr,low, high);
        }
    }
    public static void merge(int[] arr, int low, int high){
        int mid = low + ((high - low) >> 1);
        int[] helper = arr.clone();
        int index = low;
        int left = low;
        int right = mid + 1;
        while(left <= mid && right <= high){
            if(helper[left] <= helper[right]){
                arr[index] = helper[left++];
            }else{
                arr[index] = helper[right++];
            }
            index++;
        }
        while(left<= mid){
            arr[index++] = helper[left++];
        }
    }
}
