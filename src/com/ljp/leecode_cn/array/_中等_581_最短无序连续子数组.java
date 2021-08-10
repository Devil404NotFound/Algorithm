package com.ljp.leecode_cn.array;

import java.util.Arrays;

/** 每日一题 2021.08.03
 * @author lijunpeng
 * @date 2021/8/3 23:18
 * @Description
581. 最短无序连续子数组
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。



示例 1：

输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
示例 2：

输入：nums = [1,2,3,4]
输出：0
示例 3：

输入：nums = [1]
输出：0


提示：

1 <= nums.length <= 104
-105 <= nums[i] <= 105
 */
public class _中等_581_最短无序连续子数组 {
    /**
     *
     * @param nums
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了60.08%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了79.37%的用户
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] newNums = nums.clone();
        Arrays.sort(newNums);
        int left = 0, right = nums.length - 1;
        while(newNums[left] == nums[left]) {
            ++left;
            if(left == nums.length) {
                return 0;
            }
        }
        while(newNums[right] == nums[right]) {
            --right;
        }
        return right - left + 1;
    }

    /**
     * 官方题解二：一次遍历
     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leet-yhlf/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    40.2 MB, 在所有 Java 提交中击败了5.14%的用户
     */
    public int findUnsortedSubarray2(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

}
