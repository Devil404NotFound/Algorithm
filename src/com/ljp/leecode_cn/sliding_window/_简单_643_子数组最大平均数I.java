package com.ljp.leecode_cn.sliding_window;

/**每日一题 2021.02.04
 * @author lijunpeng
 * @date 2021/2/5 0:37
643. 子数组最大平均数 I
给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。



示例：

输入：[1,12,-5,-6,50,3], k = 4
输出：12.75
解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75


提示：

1 <= k <= n <= 30,000。
所给数据范围 [-10,000，10,000]。
 */
public class _简单_643_子数组最大平均数I {
    /**
     *
     * @param nums
     * @param k
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    42.9 MB, 在所有 Java 提交中击败了25.44%的用户
     */
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        for(int i = 0; i < k; ++i) {
            sum += nums[i];
        }
        long maxSum = sum;
        for(int i = k; i < nums.length; ++i) {
            sum -= nums[i - k];
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return (double)maxSum / k;
    }
}
