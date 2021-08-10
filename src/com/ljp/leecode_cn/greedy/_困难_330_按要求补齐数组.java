package com.ljp.leecode_cn.greedy;

/** 每日一题 2020.12.29
 * @author ljp
 * @date 2020/12/29 23:42
330. 按要求补齐数组
给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。

示例 1:

输入: nums = [1,3], n = 6
输出: 1
解释:
根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
所以我们最少需要添加一个数字。
示例 2:

输入: nums = [1,5,10], n = 20
输出: 2
解释: 我们需要添加 [2, 4]。
示例 3:

输入: nums = [1,2,2], n = 5
 */
public class _困难_330_按要求补齐数组 {
    /**
     * 官方题解：贪心算法
     1. 如果[1, x-1]都覆盖了，那么添加一个x，就能覆盖[1, 2x-1]
     2. 而nums有初始数组，那么初始化下标index为0，取nums[index]的值，
        如果小于等于x,那么x就可以加上nums[index](因为[1, x-1]都覆盖了，那么多了nums[index]，意味着可以覆盖[x, x+nums[index]]了
     3. 终止条件为x > n

     * @param nums
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38 MB, 在所有 Java 提交中击败了84.21%的用户
     */
    public int minPatches(int[] nums, int n) {
        long x = 1;
        int length = nums.length;
        int index = 0;
        int patches = 0;
        while(x <= n) {
            if(index < length && nums[index] <= x) {
                x += nums[index];
                ++index;
            }else {
                x *= 2;
                ++patches;
            }
        }
        return patches;
    }
}
