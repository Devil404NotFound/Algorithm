package com.ljp.leecode_cn.sliding_window;

import java.util.Arrays;

/** 每日一题 2021.07.19
 * @author lijunpeng
 * @date 2021/7/19 23:39
 * @Description
1838. 最高频元素的频数
元素的 频数 是该元素在一个数组中出现的次数。

给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。

执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。



示例 1：

输入：nums = [1,2,4], k = 5
输出：3
解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
4 是数组中最高频元素，频数是 3 。
示例 2：

输入：nums = [1,4,8,13], k = 5
输出：2
解释：存在多种最优解决方案：
- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
示例 3：

输入：nums = [3,9,6], k = 2
输出：1


提示：

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= k <= 105
 */
public class _中等_1838_最高频元素的频数 {
    /**
     * 滑动窗口
     * 出现的问题：
     * 1. newK会溢出
     * 2. max最小为1
     * 3. nums[right] - nums[right-1]需要括号括起来
     * @param nums
     * @param k
     * @return
    添加备注

    执行用时：
    32 ms, 在所有 Java 提交中击败了96.99%的用户
    内存消耗：
    54.4 MB, 在所有 Java 提交中击败了10.69%的用户
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int max = 1;
        long newK = k;
        int left = 0, right = 1;
        //滑动窗口
        while(right < nums.length) {
            newK -= (nums[right] - nums[right - 1]) * (right - left);
            while(newK < 0) {
                newK += nums[right] - nums[left];
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    /**
     * 官方题解一：滑动窗口
     * @param nums
     * @param k
     * @return
    执行用时：
    35 ms, 在所有 Java 提交中击败了47.07%的用户
    内存消耗：
    54.5 MB, 在所有 Java 提交中击败了7.60%的用户
     */
    public int maxFrequency2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
