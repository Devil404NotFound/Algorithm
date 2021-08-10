package com.ljp.leecode_cn.sort;

/**每日一题2020.11.07【解法高级】
 *
 327. 区间和的个数
 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

 说明:
 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。

 示例:

 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 输出: 3
 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 * @author ljp
 * @date 2020/11/7 9:37
 */
public class _困难_327_区间和的个数 {
    public static void main(String[] args) {
        int[] nums = {2147483647,-2147483648,-1,0};
        int lower = -1;
        int upper = 0;
        _困难_327_区间和的个数 t = new _困难_327_区间和的个数();
        int count = t.countRangeSum2(nums, lower, upper);
        System.out.println(count);
    }

    /**
     * 前缀和
     * @param nums
     * @param lower
     * @param upper
     * @return
    执行用时：
    166 ms, 在所有 Java 提交中击败了29.75%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了88.68%的用户
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        //前缀pre[i]不包括nums[i]
        long[] pre = new long[nums.length + 1];
        int count = 0;
        for (int right = 1; right < pre.length; right++) {
            pre[right] = pre[right - 1] + nums[right - 1];
            for (int left = 0; left < right; left++) {
                if(pre[right] - pre[left] >= lower && pre[right] - pre[left] <= upper){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
    执行用时：
    8 ms, 在所有 Java 提交中击败了89.70%的用户
    内存消耗：
    38.9 MB, 在所有 Java 提交中击败了66.98%的用户
     */
    public int countRangeSum2(int[] nums, int lower, int upper) {
        long[] pre = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        return countRangeSumRecursive(pre, lower, upper, 0, nums.length);
    }
    private int countRangeSumRecursive(long[] pre, int lower, int upper, int left, int right) {
        if(left == right){
            return 0;
        }
        int mid = (left + right) / 2;
        int n1 = countRangeSumRecursive(pre, lower, upper, left, mid);
        int n2 = countRangeSumRecursive(pre, lower, upper, mid + 1, right);
        int ret = n1 + n2;
        //计算满足条件的区间(i, j)
        int i  = left;
        int l = mid + 1;
        int r = mid + 1;
        while(i <= mid){
            while(l <= right && pre[l] - pre[i] < lower){
                l++;
            }
            while(r <= right && pre[r] - pre[i] <= upper){
                r++;
            }
            ret += r - l;
            i++;
        }
        //归并排序
        long[] sorted = new long[right - left + 1];
        int p = 0;
        int p1 = left, p2 = mid + 1;
        while(p1 <= mid || p2 <= right){
            if(p1 > mid){
                sorted[p] = pre[p2++];
            }else if(p2 > right){
                sorted[p] = pre[p1++];
            }else{
                if(pre[p1] < pre[p2]){
                    sorted[p] = pre[p1++];
                }else{
                    sorted[p] = pre[p2++];
                }
            }
            p++;
        }
        while(p1 <= mid){
            sorted[p++] = pre[p1++];
        }
        for (i = 0; i < sorted.length; i++) {
            pre[left + i] = sorted[i];
        }
        return ret;
    }

}
