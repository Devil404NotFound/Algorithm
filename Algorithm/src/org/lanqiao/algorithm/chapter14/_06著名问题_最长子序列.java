package org.lanqiao.algorithm.chapter14;
/*
长增子序列的长度
输入
    4 2 3 1 5
输出
    3
(因为2 3 5组成了最长递增子序列）
 */
public class _06著名问题_最长子序列 {
    public static void main(String[] args) {
        int[] arr = {12, 13, 11, 9, 6, 10, 15, 20, 17, 8, 14, 18, 16};
        System.out.println(dp(arr));
    }
    public static int dp(int[] arr){
        int[] helpArr = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if(arr[i] > arr[j]){
                    if(helpArr[i] <= helpArr[j]){
                        helpArr[i] = helpArr[j] + 1;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(max < helpArr[i]){
                max = helpArr[i];
            }
        }
        //最长子序列不算上自己，最后要加1
        return max + 1;
    }
}
