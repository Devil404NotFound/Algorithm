package org.lanqiao.algorithm.chapter04;

import org.lanqiao.Utils.Util;

public class _03快速排序三个指针扫描 {
    public static void main(String[] args) {
        int[] arr = {5,9,8,3,6,4,1,10,0,1,2,5,6,8};
//        quickSort(arr);
        quickSort(arr, 0, arr.length - 1);
        Util.print(arr);

    }
    public static void quickSort(int[] arr, int left, int right){
        if(left >= right){
            return;
        }
        int[] p = partition(arr, left, right);
        quickSort(arr,left, p[0] - 1);
        quickSort(arr, p[1] + 1, right);
    }
    public static int[] partition(int[] arr, int left, int right){
        /*int mid = (left + right) >> 1;
        int indexMid = right;
        if(arr[mid] >= arr[left] && arr[mid] <= right){
            indexMid = mid;
        }else if(arr[left] >= arr[mid] && arr[left] <= arr[right]){

        }*/

        int[] p = new int[2];
        int pivot = arr[left];
        int s = left + 1;
        int e = left;
        int bigger = right;
        while(s <= bigger){
            if(arr[s] < pivot){
                Util.swap(arr,e,s);
                s++;
                e++;

            }else if(arr[s] == pivot){
                s++;
            }else{
                Util.swap(arr, s, bigger);
                bigger--;
            }
        }
        p[0] = e;
        p[1] = bigger;
        return p;
    }
}
