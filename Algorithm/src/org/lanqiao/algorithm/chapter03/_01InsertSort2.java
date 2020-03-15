package org.lanqiao.algorithm.chapter03;

import org.lanqiao.Utils.Util;

public class _01InsertSort2 {
    public static void main(String[] args) {
        int[] arr = {8,9,7,5,6,4,2,3,1};
        Util.print(arr);
        insertSort2(arr, arr.length - 1);
        Util.print(arr);
    }

    private static void insertSort2(int[] arr, int index) {
        if(index < 1){
            return;
        }
        insertSort2(arr,index - 1);
        int temp = arr[index];
        int i = index - 1;
        while( i > -1 && temp < arr[i]){
            arr[i + 1] = arr[i];
            i--;
        }
        arr[i + 1] = temp;
    }
}
