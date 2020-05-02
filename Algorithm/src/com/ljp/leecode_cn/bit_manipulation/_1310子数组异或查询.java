package com.ljp.leecode_cn.bit_manipulation;

import org.lanqiao.Utils.Util;

/**
 * 1310. 子数组异或查询【中等】
 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。

 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。

 并返回一个包含给定查询 queries 所有结果的数组。



 示例 1：

 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 输出：[2,7,14,8]
 解释：
 数组中元素的二进制表示形式是：
 1 = 0001
 3 = 0011
 4 = 0100
 8 = 1000
 查询的 XOR 值为：
 [0,1] = 1 xor 3 = 2
 [1,2] = 3 xor 4 = 7
 [0,3] = 1 xor 3 xor 4 xor 8 = 14
 [3,3] = 8
 示例 2：

 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 输出：[8,0,4,4]


 提示：

 1 <= arr.length <= 3 * 10^4
 1 <= arr[i] <= 10^9
 1 <= queries.length <= 3 * 10^4
 queries[i].length == 2
 0 <= queries[i][0] <= queries[i][1] < arr.length
 */
public class _1310子数组异或查询 {
    public static void main(String[] args) {
        int[] arr = {1,3,4,8};
        int[][] queries = {{0,1},{1,2},{0,3},{3,3}};
        Util.print(xorQueries2(arr, queries));
    }

    /**
     * 超出内存限制
     * @param arr
     * @param queries
     * @return
     */
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int[][] dp = new int[arr.length][arr.length];
        int[] res = new int[queries.length];
        //建立一个异或表
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = arr[i];
            for (int j = i + 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i][j - 1]  ^ arr[j];
            }
        }
        for (int i = 0; i < queries.length; i++) {
            res[i] = dp[queries[i][0]][queries[i][1]];
        }
        return res;
    }

    /**
     *
     * @param arr
     * @param queries
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了99.59%的用户
    内存消耗 :
    49.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int[] xorQueries2(int[] arr, int[][] queries) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        int[] res = new int[queries.length];
        int j = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            left[i + 1] = left[i] ^ arr[i];
            right[j - 1] = right[j] ^ arr[j];
            j--;
        }
        int all = right[0] ^ arr[0];
        int l, r;
        for (int i = 0; i < queries.length; i++) {
            l = queries[i][0];
            r = queries[i][1];
            res[i] = all ^ left[l] ^ right[r];
        }
        return res;
    }

    /**
     *
     * @param arr
     * @param queries
     * @return
     * 官方思路，前缀和，【惊艳】
     * 执行用时 :
    2 ms
    , 在所有 Java 提交中击败了99.59%的用户
    内存消耗 :
    50.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int[] xorQueries3(int[] arr, int[][] queries) {
        int[] pre = new int[arr.length];
        int[] res = new int[queries.length];
        pre[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre[i] = pre[i - 1] ^ arr[i];
        }
        int l,r;
        for (int i = 0; i < queries.length; i++) {
            l = queries[i][0];
            r = queries[i][1];
            res[i] = pre[l] ^ pre[r] ^ arr[l];
        }
        return res;
    }
}
