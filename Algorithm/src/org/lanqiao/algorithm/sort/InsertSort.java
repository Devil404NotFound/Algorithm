package org.lanqiao.algorithm.sort;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        insertSort(arr);
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }

    }
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int index = arr[i];
            while(j > -1 && index < arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = index;
        }
    }
}
