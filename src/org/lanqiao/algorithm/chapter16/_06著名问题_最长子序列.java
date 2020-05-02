package org.lanqiao.algorithm.chapter16;
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
//        int[] arr = {12, 13, 11, 9, 6, 10, 15, 20, 17, 8, 14, 18, 16};
        int[] arr= {4, 2, 3, 1, 5};
        System.out.println(dp(arr));
        System.out.println(dp2(arr));
    }

    /**
     * 动态规划解决
     * @param arr
     * @return
     */
    public static int dp(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if(arr[i] > arr[j]){
                    if(dp[i] <= dp[j]){
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(max < dp[i]){
                max = dp[i];
            }
        }
        //最后要把第一个算上（最后加一或者初始化dp数组为1
        return max + 1;

    }

    /**
     * 改进后的动态规划=====只用1维dp数组，下标表示的是最大数组长度，
     * @param arr
     * @return
     */
    public static int dp2(int[] arr){
        int[] dp = new int[arr.length + 1];
        int p = 0;
        for (int i = 0; i < arr.length; i++) {
            if(dp[p] < arr[i]){
                dp[++p] = arr[i];
            }else{
                for (int j = 1; j <= p; j++) {
                    if(dp[j] > arr[i]){
                        dp[j] = arr[i];
                        break;
                    }
                }
            }
        }
        return p;
    }
}
