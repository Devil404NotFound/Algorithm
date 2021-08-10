package com.ljp.leecode_cn.array;

import java.util.Arrays;

/** 每日一题 2021.02.16
 * @author lijunpeng
 * @date 2021/2/16 23:49
561. 数组拆分 I
给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。

返回该 最大总和 。



示例 1：

输入：nums = [1,4,3,2]
输出：4
解释：所有可能的分法（忽略元素顺序）为：
1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
所以最大总和为 4
示例 2：

输入：nums = [6,2,6,5,1,2]
输出：9
解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9


提示：

1 <= n <= 104
nums.length == 2 * n
-104 <= nums[i] <= 104
 */
public class _简单_561_数组拆分I {
    /**
     * 排序
     * @param nums
     * @return
    执行用时：
    13 ms, 在所有 Java 提交中击败了96.18%的用户
    内存消耗：
    41.3 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    public int arrayPairSum(int[] nums) {
        int[] cloneNums = nums.clone();
        Arrays.sort(cloneNums);
        int ans = 0;
        for(int i = 0; i < nums.length; i += 2) {
            ans += cloneNums[i];
        }
        return ans;
    }
}
