package com.ljp.leecode_cn.greedy;

import org.lanqiao.Utils.Util;

/**每日一题 2020.12.02
 单调栈
 类似题目
 316. 去除重复字母（困难）
 321. 拼接最大数（困难）
 402. 移掉 K 位数字（中等）
 1081. 不同字符的最小子序列（中等）

 * @author ljp
 * @date 2020/12/2 15:39

321. 拼接最大数
给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。

求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。

说明: 请尽可能地优化你算法的时间和空间复杂度。

示例 1:

输入:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
输出:
[9, 8, 6, 5, 3]
示例 2:

输入:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
输出:
[6, 7, 6, 0, 4]
示例 3:

输入:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
输出:
[9, 8, 9]

 */
public class _困难_321_拼接最大数 {
    public static void main(String[] args) {
        _困难_321_拼接最大数 test = new _困难_321_拼接最大数();
        int[] nums1 = {3, 9};
        int[] nums2 = {8, 9};
        int k = 3;
        int[] ans = test.maxNumber(nums1, nums2, k);
        Util.print(ans);
    }

    /**
     * 单调栈
     * @param nums1
     * @param nums2
     * @param k
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了86.95%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了77.92%的用户

     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] maxSubsequence = new int[k];//存结果
        int m = nums1.length, n = nums2.length;
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] maxCurrentSubsequence = merge(subsequence1, subsequence2);
            if(compare(maxCurrentSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(maxCurrentSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }
    //求长度为k的单调递减栈
    private int[] maxSubsequence(int[] nums, int k) {
        int[] stack = new int[k];
        int len = nums.length;
        int top = -1, remain = len - k;
        for (int i = 0; i < len; i++) {
            while(top >= 0 && stack[top] < nums[i] && remain > 0) {
                --top;
                --remain;
            }
            if(top < k - 1){
                stack[++top] = nums[i];
            }else{
                --remain;
            }
        }
        return stack;
    }
    //归并排序
    private int[] merge(int[] nums1, int[] nums2) {
        int x = nums1.length, y = nums2.length;
        int k = x + y;
        if(x == 0) {
            return nums2;
        }
        if(y == 0) {
            return nums1;
        }
        int[] merged = new int[k];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < k; i++) {
            if(compare(nums1, index1, nums2, index2) > 0) {
                merged[i] = nums1[index1++];
            }else{
                merged[i] = nums2[index2++];
            }
        }
        return merged;
    }
    //自定义一个比较器
    private int compare(int[] nums1, int index1, int[] nums2, int index2) {
        int x = nums1.length, y = nums2.length;
        while(index1 < x && index2 < y) {
            int difference = nums1[index1] - nums2[index2];
            if(difference != 0){
                return difference;
            }
            ++index1;
            ++index2;
        }
        return (x - index1) - (y - index2);
    }
}
