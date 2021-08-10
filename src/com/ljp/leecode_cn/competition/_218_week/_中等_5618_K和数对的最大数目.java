package com.ljp.leecode_cn.competition._218_week;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljp
 * @date 2020/12/6 11:31
5618. K 和数对的最大数目 显示英文描述
通过的用户数2197
尝试过的用户数2493
用户总通过次数2211
用户总提交次数4597
题目难度Medium
给你一个整数数组 nums 和一个整数 k 。

每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。

返回你可以对数组执行的最大操作数。



示例 1：

输入：nums = [1,2,3,4], k = 5
输出：2
解释：开始时 nums = [1,2,3,4]：
- 移出 1 和 4 ，之后 nums = [2,3]
- 移出 2 和 3 ，之后 nums = []
不再有和为 5 的数对，因此最多执行 2 次操作。
示例 2：

输入：nums = [3,1,3,4,3], k = 6
输出：1
解释：开始时 nums = [3,1,3,4,3]：
- 移出前两个 3 ，之后nums = [1,4,3]
不再有和为 6 的数对，因此最多执行 1 次操作。


提示：

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 109
 */
public class _中等_5618_K和数对的最大数目 {
    //通过
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            int sum = map.getOrDefault(num, 0);
            map.put(num, sum + 1);
        }
        int count = 0;
        for(int num : nums){
            int left = map.getOrDefault(num, 0);
            int right = map.getOrDefault(k - num, 0);
            if(num == k - num) {
                count += left >> 1;
                map.put(num, 0);
            }else if(left > 0 && right > 0) {
                int min = Math.min(left, right);
                count += min;
                map.put(num, left - min);
                map.put(k - num, right - min);
            }
        }
        return count;
    }
}
