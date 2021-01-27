package com.ljp.leecode_cn.array;

import java.util.Arrays;

/** 每日一题 2021.01.28
 * @author lijunpeng
 * @date 2021/1/28 0:24
724. 寻找数组的中心索引
给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。

我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。

如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。



示例 1：

输入：
nums = [1, 7, 3, 6, 5, 6]
输出：3
解释：
索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
同时, 3 也是第一个符合要求的中心索引。
示例 2：

输入：
nums = [1, 2, 3]
输出：-1
解释：
数组中不存在满足此条件的中心索引。


说明：

nums 的长度范围为 [0, 10000]。
任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 */
public class _简单_724_寻找数组的中心索引 {
    /**
     * 前缀和
     * @param nums
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了20.60%的用户
     */
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        //前缀和
        int[] pre = new int[n + 1];
        for(int i = 1; i < pre.length; ++i) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        for(int i = 0; i < n; ++i) {
            //判断索引i左边是否等于右边
            if(2 * pre[i] == pre[n] - nums[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 官方题解：前缀和
     * @param nums
     * @return
    执行用时：
    7 ms, 在所有 Java 提交中击败了19.65%的用户
    内存消耗：
    39.7 MB, 在所有 Java 提交中击败了5.14%的用户
     */
    public int pivotIndex2(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}
