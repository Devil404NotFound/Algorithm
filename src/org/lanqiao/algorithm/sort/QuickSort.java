package org.lanqiao.algorithm.sort;

import org.lanqiao.Utils.Util;



public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5,9,8,3,6,4,1,10,0};
//        quickSort(arr);
        quickSort2(arr, 0, arr.length - 1);
        Util.print(arr);
    }

    /**
     * 我的快排
     * @param arr
     */
    public static void quickSort(int[] arr){
        function(arr,0, arr.length - 1);

    }
    public static void function(int[] arr, int low, int high){
        if(low >= high){
            return;
        }
        int i = low;
        int j = high;
        int index = arr[j];
        while(i < j) {
            while (i < j && arr[i] <= index) {
                i++;
            }
            arr[j] = arr[i];
            while (i < j && arr[j] >= index) {
                j--;
            }
            arr[i] = arr[j];
        }
        arr[i] = index;
        //System.out.println("low:"+low+" --- high:" + high + " --- index:" + index);
        //Util.print(arr);

        function(arr, low, i - 1);
        function(arr, i + 1, high);
    }

    public static void quickSort2(int[] arr, int left, int right){
        if(left >= right){
            return;
        }
        int p = partition(arr, left, right);
        quickSort2(arr, left, p - 1);
        quickSort2(arr, p + 1, right);
    }
    public static int partition(int[] arr, int p, int r){
        int pivot = arr[p];
        int left = p + 1;
        int right = r;
        while(left <= right){
            while(left <= right && pivot >= arr[left]){
                left++;
            }
            while(right >= 0 && pivot < arr[right]){
                right--;
            }
            if(left < right){
                Util.swap(arr, left, right);
            }
        }
        Util.swap(arr,p, right);
        return right;
    }

}
