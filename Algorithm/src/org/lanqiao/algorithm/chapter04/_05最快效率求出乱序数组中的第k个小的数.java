package org.lanqiao.algorithm.chapter04;

import org.lanqiao.Utils.Util;

/**
 * 最快效率求出乱序数组中的第k小的数
 *（可以充分利用快排分区的思路）
 */
public class _05最快效率求出乱序数组中的第k个小的数 {
    public static void main(String[] args) {
        int[] arr = {3,4,1,8,2,0};
        int res = minOfk(arr, 2, 0, arr.length - 1);
        System.out.println(res);
        Util.print(arr);
    }
    public static int minOfk(int[] arr, int k, int low, int high){
        if(low < high){
            int p = partition(arr, k, low, high);
            if(p + 1 > k){
                 return minOfk(arr, k, low,p - 1);
            }else if(p + 1 < k){
                return minOfk(arr, k, p+1, high);
            }else{
                return arr[p];
            }
        }
        return -1;
    }
    public static int partition(int[] arr, int k, int low, int high){
        int pivot = arr[low];
        int left = low + 1;
        int right = high;
        while(left <= right){
            while(left <= right && arr[left] <= pivot){
                left++;
            }
            while(left <= right && arr[right] > pivot){
                right--;
            }
            if(left < right){
                Util.swap(arr, left, right);
            }
        }
        Util.swap(arr, low, right);
        return right;
    }
}
