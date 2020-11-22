package com.ljp.leecode_cn.problom;

/**
 * @author ljp
 * @date 2020/11/22 11:10
 *
5607. 生成平衡数组的方案数

题目难度Medium
给你一个整数数组 nums 。你需要选择 恰好 一个下标（下标从 0 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。

比方说，如果 nums = [6,1,7,4,1] ，那么：

选择删除下标 1 ，剩下的数组为 nums = [6,7,4,1] 。
选择删除下标 2 ，剩下的数组为 nums = [6,1,4,1] 。
选择删除下标 4 ，剩下的数组为 nums = [6,1,7,4] 。
如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。

请你返回删除操作后，剩下的数组 nums 是 平衡数组 的 方案数 。



示例 1：

输入：nums = [2,1,6,4]
输出：1
解释：
删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
只有一种让剩余数组成为平衡数组的方案。
示例 2：

输入：nums = [1,1,1]
输出：3
解释：你可以删除任意元素，剩余数组都是平衡数组。
示例 3：

输入：nums = [1,2,3]
输出：0
解释：不管删除哪个元素，剩下数组都不是平衡数组。


提示：

1 <= nums.length <= 105
1 <= nums[i] <= 104
 */
public class _5607_生成平衡数组的方案数 {
    public static void main(String[] args) {
        _5607_生成平衡数组的方案数 test = new _5607_生成平衡数组的方案数();
        int[] nums = {2,1,6,4};
        int ans = test.waysToMakeFair2(nums);
        System.out.println(ans);
    }
    //通过
    public int waysToMakeFair(int[] nums) {
        int count = 0;
        int n = nums.length;
        int[] evenPre = new int[n + 2];
        int[] oddPre = new int[n + 2];
        for(int i = 0; i < n; i++) {
            if((i & 1) == 0) {
                evenPre[i + 1] = evenPre[i] + nums[i];
                oddPre[i + 1] = oddPre[i];
            }else{
                evenPre[i + 1] = evenPre[i];
                oddPre[i + 1] = oddPre[i] + nums[i];
            }
        }
        int even, odd;
        for(int i = 0; i < n; i++) {
            //偶数位的i前面都是偶数位，i后面的用奇数位（删除了nums[i]后，i后面奇数位变偶数位）
            even = evenPre[i] + oddPre[n] - oddPre[i];
            //奇数位的i前面都是奇数位，i后面的用偶数位
            odd = oddPre[i] + evenPre[n] - evenPre[i];
            //删除nums[i]这个数
            if((i & 1) == 0){
                odd -= nums[i];
            }else{
                even -= nums[i];
            }
            if(even == odd){
                count++;
            }
        }
        return count;
    }

    /**
     * 大神解法@cuiaoxiang
     * @param nums
     * @return
     */
    public int waysToMakeFair2(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            //前缀和，偶数位加，奇数位减
            pre[i + 1] = pre[i] + ((i % 2) == 0 ? nums[i] : -nums[i]);
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int cur = pre[i] - (pre[n] - pre[i + 1]);
            if(cur == 0){
                ++ret;
            }
        }
        return ret;
    }
}
