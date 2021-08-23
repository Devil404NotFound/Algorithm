package com.ljp.leecode_cn.array;

import java.util.Arrays;

/** 每日一题 2021.08.23
 * @author lijunpeng
 * @date 2021/8/23 22:56
 * @Description
1646. 获取生成数组中的最大值
给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：

nums[0] = 0
nums[1] = 1
当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
返回生成数组 nums 中的 最大 值。



示例 1：

输入：n = 7
输出：3
解释：根据规则：
nums[0] = 0
nums[1] = 1
nums[(1 * 2) = 2] = nums[1] = 1
nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
nums[(2 * 2) = 4] = nums[2] = 1
nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
nums[(3 * 2) = 6] = nums[3] = 2
nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
示例 2：

输入：n = 2
输出：1
解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
示例 3：

输入：n = 3
输出：2
解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2


提示：

0 <= n <= 100
 */
public class _简单_1646_获取生成数组中的最大值 {
    /**
    * @Author lijunpeng
    * @Date 2021/8/23 22:56
    * @Description
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    34.9 MB, 在所有 Java 提交中击败了94.23%的用户
    **/
    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        int max = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; ++i) {
            if ((i & 1) == 1) {
                nums[i] = nums[i >> 1] + nums[(i >> 1) + 1];
            } else {
                nums[i] = nums[i >> 1];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }
    /** 官方题解：找nums[i]规律
    * @Author lijunpeng
    * @Date 2021/8/23 22:59
    * @Description
    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/get-maximum-in-generated-array/solution/huo-qu-sheng-cheng-shu-zu-zhong-de-zui-d-0z2l/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    **/
    public int getMaximumGenerated2(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        for (int i = 2; i <= n; ++i) {
            nums[i] = nums[i / 2] + i % 2 * nums[i / 2 + 1];
        }
        return Arrays.stream(nums).max().getAsInt();
    }
}
