package org.lanqiao.algorithm.chapter02;

public class _03BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
        }
        System.out.println(binarySearch(arr,62,0,arr.length-1));
    }

    /**
     * 递归实现二分查找
     * @param arr
     * @param num
     * @param low
     * @param high
     * @return
     */
    public static int binarySearch(int[] arr, int num, int low, int high){
        int binary = low + ((high - low) >> 1);
        if(low > high){
            return -1;
        }
        if(num < arr[binary]){
           return binarySearch(arr, num, low, binary-1);
        } else if(num > arr[binary]){
            return binarySearch(arr, num, binary + 1, high);
        }else{
            return binary;
        }
    }
}
