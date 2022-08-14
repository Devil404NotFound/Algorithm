package com.ljp.leecode_cn.greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lijunpeng
 * @date 2022/7/9 0:52
 * @description
 **/

public class _中等_873_最长的斐波那契子序列的长度 {
    public static void main(String[] args) {
        _中等_873_最长的斐波那契子序列的长度 test = new _中等_873_最长的斐波那契子序列的长度();
        int[] arr = new int[]{2,4,5,6,7,8,11,13,14,15,21,22,34};
        int result = test.lenLongestFibSubseq(arr);
        System.out.println(result);
    }
    /** 暴力算法
    * @Author lijunpeng
    * @Date 2022/7/9 15:25
    */
    public int lenLongestFibSubseq(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for(int num : arr) {
            set.add(num);
        }
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int curMax = 2;
                int nextNum = arr[j];
                int number = arr[i] + nextNum;
                while(set.contains(number)) {
                    int cur = number + nextNum;
                    nextNum = number;
                    number = cur;
                    curMax++;
                }
                max = Math.max(max, curMax);
            }
        }
        return max < 3 ? 0 : max;
    }

    /** 官方题解：动态规划
    * @Author lijunpeng
    * @Date 2022/7/9 15:34
    */
    public int lenLongestFibSubseq2(int[] arr) {
        Map<Integer, Integer> indices = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            indices.put(arr[i], i);
        }
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[i] - arr[j] < arr[j]; j--) {
                int k = indices.getOrDefault(arr[i] - arr[j], -1);
                if(k > -1) {
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }
}
