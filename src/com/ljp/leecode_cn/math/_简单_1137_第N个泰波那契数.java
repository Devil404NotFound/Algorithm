package com.ljp.leecode_cn.math;

/** 每日一题 2021.08.08
 * @author lijunpeng
 * @date 2021/8/8 22:51
 * @Description
1137. 第 N 个泰波那契数
泰波那契序列 Tn 定义如下：

T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2

给你整数 n，请返回第 n 个泰波那契数 Tn 的值。



示例 1：

输入：n = 4
输出：4
解释：
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
示例 2：

输入：n = 25
输出：1389537


提示：

0 <= n <= 37
答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
通过次数58,275提交次数97,320
 */
public class _简单_1137_第N个泰波那契数 {
    /**
     * 动态规划
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35 MB, 在所有 Java 提交中击败了82.47%的用户
     */
    public int tribonacci(int n) {
        int[] nums = new int[]{0, 1, 1, 2};
        for(int i = 4; i <= n; ++i) {
            nums[i % 4] = nums[(i - 1) % 4] + nums[(i - 2) % 4] + nums[(i - 3) % 4];
        }
        return nums[n % 4];
    }
}
