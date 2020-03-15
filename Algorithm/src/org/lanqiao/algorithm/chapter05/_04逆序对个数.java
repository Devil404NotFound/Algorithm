package org.lanqiao.algorithm.chapter05;

import org.lanqiao.Utils.Util;

/**逆数对个数
 * 一个数列，如果左边的数大，右边的数小，则称这两个数为一个逆序对
 * 求出一个数列中有多少个逆数对
 */
public class _04逆序对个数 {
    public static void main(String[] args) {
//        int[] arr = {2,5,1,3,4};//结果是4
        int[] arr = {37,40,48,90,32,5,12,3,44,13};
        int res = mergeSort(arr, 0, arr.length - 1);
        System.out.println(res);
        Util.print(arr);
    }
    public static int mergeSort(int[] arr,int low, int high){
        if(low < high){
            int mid = (low + high) >> 1;
            return mergeSort(arr, low, mid) + mergeSort(arr, mid+1, high) + merge(arr, low, high);
        }
        return 0;
    }
    public static int merge(int[] arr, int low, int high){
        int nixu = 0;
        int[] helper = arr.clone();
        int index = low;
        int mid = (low + high) >> 1;
        int left = low;
        int right = mid + 1;
        while(left <= mid && right <= high){
            if(helper[left] <= helper[right]){
                arr[index] = helper[left];
                left++;
            }else{
                arr[index] = helper[right];
                right++;
                nixu += mid - left + 1;
            }
            index++;
        }

        while(left <= mid){
            arr[index] = helper[left];
            index++;
            left++;
        }
        return nixu;
    }
}
