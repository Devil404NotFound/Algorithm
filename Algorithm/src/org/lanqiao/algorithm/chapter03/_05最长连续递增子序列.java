package org.lanqiao.algorithm.chapter03;

import org.lanqiao.Utils.Util;

/**
 * （1,9,2,5,7，3,4,6,8,0）中最长递增子序列为（3,4,6,8）。
 */
public class _05最长连续递增子序列 {
    public static void main(String[] args) {
        int[] arr = {1,9,2,5,7,3,4,6,8,0};
        int[] resultArr = longest(arr);
        Util.print(resultArr);
    }
    public static int[] longest(int[] arr){
        int begin = 0;
        int length = 1;
        int maxBegin = 0;
        int maxLength = 1;
        for (int i = 0; i < arr.length - 1; i++){
            if(arr[i] < arr[i + 1]){
                length++;
            }else{
                if(length > maxLength){
                    maxLength = length;
                    maxBegin = begin;
                }
                length = 1;
                begin = i + 1;
            }
        }
        int[] resultArr = new int[maxLength];
        for (int i = 0; i <resultArr.length ; i++) {
            resultArr[i] = arr[i + maxBegin];
        }
        return resultArr;
    }
}
