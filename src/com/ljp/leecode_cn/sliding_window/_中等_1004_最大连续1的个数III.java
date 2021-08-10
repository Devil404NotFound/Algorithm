package com.ljp.leecode_cn.sliding_window;

/** 每日一题 2021.02.19
 * @author lijunpeng
 * @date 2021/2/19 11:45
1004. 最大连续1的个数 III
给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

返回仅包含 1 的最长（连续）子数组的长度。



示例 1：

输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：
[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
示例 2：

输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。


提示：

1 <= A.length <= 20000
0 <= K <= A.length
A[i] 为 0 或 1
 */
public class _中等_1004_最大连续1的个数III {
    /**
     *
     * @param A
     * @param K
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了94.07%的用户
    内存消耗：
    40 MB, 在所有 Java 提交中击败了10.33%的用户
     */
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        int left = 0, right = 0;
        int count = K;//记录K值
        int ans = 0;
        while(right < n) {
            //right向右滑动一格
            count -= A[right] ^ 1;
            right++;
            //如果K值用过头了，left就右滑一格
            while(count < 0) {
                count += A[left] ^ 1;
                left++;
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
