package com.ljp.leecode_cn.array;

/**每日一题 2021.01.24
 * @author lijunpeng
 * @date 2021/1/24 10:51
674. 最长连续递增序列
给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。

连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。



示例 1：

输入：nums = [1,3,5,4,7]
输出：3
解释：最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
示例 2：

输入：nums = [2,2,2,2,2]
输出：1
解释：最长连续递增序列是 [2], 长度为1。


提示：

0 <= nums.length <= 104
-109 <= nums[i] <= 109
 */
public class _简单_674_最长连续递增序列 {
    /**
     *思路：用sum记录最长子序列
     * @param nums
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了99.83%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了11.61%的用户
     */
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int max = 0;
        int sum = 1;
        for(int i = 1;i < nums.length; ++i) {
            if(nums[i - 1] < nums[i]) {
                ++sum;
            }else{
                max = Math.max(max, sum);
                sum = 1;
            }
        }
        return Math.max(max, sum);
    }

    /**
     * 官方题解：贪心算法
     * 思路：记录开始下标
     * @param nums
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了46.77%的用户
    内存消耗：
    39.3 MB, 在所有 Java 提交中击败了37.65%的用户
     */
    public int findLengthOfLCIS2(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if(i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }
}
