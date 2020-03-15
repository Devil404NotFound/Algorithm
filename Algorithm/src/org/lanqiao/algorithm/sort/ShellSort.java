package org.lanqiao.algorithm.sort;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        shellSort(arr);
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void shellSort(int[] arr){
        for (int interval = arr.length / 2; interval > 0 ; interval = interval/2) {
            for (int i = interval; i < arr.length ; i++) {
                int j = i - interval;
                int index = arr[i];
                while(j > -1 && index < arr[j]){
                    arr[j + interval] = arr[j];
                    j = j - interval;
                }
                arr[j + interval] = index;
            }
        }

    }
}

