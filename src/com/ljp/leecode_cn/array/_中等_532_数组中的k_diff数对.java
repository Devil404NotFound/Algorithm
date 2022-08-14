package com.ljp.leecode_cn.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** 每日一题 2022.06.17
 * @author lijunpeng
 * @date 2022/6/16 23:56
 * @description
532. 数组中的 k-diff 数对
给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。

k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：

0 <= i, j < nums.length
i != j
nums[i] - nums[j] == k
注意，|val| 表示 val 的绝对值。



示例 1：

输入：nums = [3, 1, 4, 1, 5], k = 2
输出：2
解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
示例 2：

输入：nums = [1, 2, 3, 4, 5], k = 1
输出：4
解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
示例 3：

输入：nums = [1, 3, 1, 5, 4], k = 0
输出：1
解释：数组中只有一个 0-diff 数对，(1, 1) 。


提示：

1 <= nums.length <= 104
-107 <= nums[i] <= 107
0 <= k <= 107
 **/

public class _中等_532_数组中的k_diff数对 {
    /**
    * @Author lijunpeng
    * @Date 2022/6/17 0:22
    * @Description 官方题解一：哈希表
    */
    public int findPairs(int[] nums, int k) {
        Set<Integer> visited = new HashSet<Integer>();
        Set<Integer> res = new HashSet<Integer>();
        for (int num : nums) {
            if (visited.contains(num - k)) {
                res.add(num - k);
            }
            if (visited.contains(num + k)) {
                res.add(num);
            }
            visited.add(num);
        }
        return res.size();
    }
    public static void main(String[] args) {
        int[] nums = {1,2,4,4,3,3,0,9,2,3};
        int k = 3;
        System.out.println(new _中等_532_数组中的k_diff数对().findPairs(nums, k));
    }
    /**
    * @Author lijunpeng
    * @Date 2022/6/17 0:21
    * @Description 官方题解二：排序+双指针
    */
    public int findPairs2(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        int count = 0;
        while(left < nums.length) {
            if(left == 0 || nums[left] != nums[left - 1]) {
                while (right < nums.length && (nums[right] < nums[left] + k || right <= left)) {
                    right++;
                }
                if (right < nums.length && nums[right] == nums[left] + k) {
                    count++;
                }
            }
            left++;
        }
        return count;
    }
}
