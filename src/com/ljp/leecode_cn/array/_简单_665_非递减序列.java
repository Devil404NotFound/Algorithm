package com.ljp.leecode_cn.array;

/** 每日一题 2021.02.07
 * @author lijunpeng
 * @date 2021/2/8 0:14
665. 非递减数列
给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。

我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。



示例 1:

输入: nums = [4,2,3]
输出: true
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
示例 2:

输入: nums = [4,2,1]
输出: false
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。


说明：

1 <= n <= 10 ^ 4
- 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class _简单_665_非递减序列 {
    /**
     * 修改自官方题解二
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int cnt = 1;
        int n = nums.length;
        for(int i = 0; i < n - 1; ++i) {
            int x = nums[i];
            int y = nums[i + 1];
            if(x > y) {
                cnt--;
                if(cnt < 0) {
                    return false;
                }
                if(i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }
}
