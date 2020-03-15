package org.lanqiao.algorithm.sort;
import org.lanqiao.Utils.Util;

/**
 * 选择排序
 */

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        Util.print(arr);
        selectSort(arr);

    }
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            int min = arr[i];
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
            //Util.print(arr);
        }
    }
}
