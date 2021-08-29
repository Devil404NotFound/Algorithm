package com.ljp.leecode_cn.array;

import java.util.Arrays;
import java.util.Collections;

/** 每日一题 2021.08.29
 * @Author ljp
 * @Date 2021/8/29 12:29
1588. 所有奇数长度子数组的和
给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。

子数组 定义为原数组中的一个连续子序列。

请你返回 arr 中 所有奇数长度子数组的和 。



示例 1：

输入：arr = [1,4,2,5,3]
输出：58
解释：所有奇数长度子数组和它们的和为：
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
示例 2：

输入：arr = [1,2]
输出：3
解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
示例 3：

输入：arr = [10,11,12]
输出：66


提示：

1 <= arr.length <= 100
1 <= arr[i] <= 1000
 */
public class _简单_1588_所有奇数长度子数组的和 {
    /**
    * @description 动态规划
    * @Author ljp
    * @Date 2021/8/29 12:40
    执行用时：
    4 ms, 在所有 Java 提交中击败了12.55%的用户
    内存消耗：
    37.9 MB, 在所有 Java 提交中击败了5.06%的用户
    */
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int ans = Arrays.stream(arr).sum();
        int[][] dp = new int[n][n]; //[i,j]的子数组和
        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + arr[j];
                if(((j - i) & 1) == 0) {
                    ans += dp[i][j];
                }
            }
        }
        return ans;
    }

    /**
    * @description 官方题解二：前缀和
    * @Author ljp
    * @Date 2021/8/29 13:01
    执行用时：
    1 ms, 在所有 Java 提交中击败了62.96%的用户
    内存消耗：
    36.2 MB, 在所有 Java 提交中击败了27.33%的用户
     */
    public int sumOddLengthSubarrays2(int[] arr) {
        int n = arr.length;
        int[] prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + arr[i];
        }
        int sum = 0;
        for (int start = 0; start < n; start++) {
            for (int length = 1; start + length <= n; length += 2) {
                int end = start + length - 1;
                sum += prefixSums[end + 1] - prefixSums[start];
            }
        }
        return sum;
    }
}
