package com.ljp.leecode_cn.offer;

/**
 * @author lijunpeng
 * @date 2021/7/17 11:50
 * @Description
 */
public class _简单_42_连续子数组的最大和 {
    /**
     * 官方题解一：动态规划
     * f(i)表示以nums[i]结尾的数组最大子数组之和
     * 动态表达式：f(i) = max(f(i - 1) + num, num);
     * @param nums
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了98.83%的用户
    内存消耗：
    45.1 MB, 在所有 Java 提交中击败了18.01%的用户
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, ans = nums[0];
        for(int num : nums) {
            pre = Math.max(pre + num, num);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    /**
     * 题解评论区：前缀和
     * @param nums
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了98.83%的用户
    内存消耗：
    44.9 MB, 在所有 Java 提交中击败了41.97%的用户
     */
    public int maxSubArray2(int[] nums) {
        int min = 0;
        int ans = Integer.MIN_VALUE;
        int num = 0;
        for(int i = 0 ; i < nums.length ; i ++){
            num += nums[i];
            ans = Math.max(ans , num - min);
            if(num<min){
                min = num;
            }
        }
        return ans;
    }

    /**
     * 官方题解二：分治
     （看似复杂了，但是它不仅可以解决区间 [0, n-1][0,n−1]，还可以用于解决任意的子区间 [l,r][l,r] 的问题。
     如果用堆式存储的方式记忆化下来，即建成一颗真正的树之后，我们就可以在 O(\log n)O(logn) 的时间内求到任意区间内的答案
     这棵树就是一种神奇的数据结构——线段树。
     执行用时：
     2 ms, 在所有 Java 提交中击败了15.00%的用户
     内存消耗：
     45.8 MB, 在所有 Java 提交中击败了5.09%的用户
     */

    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray3(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
