package com.ljp.leecode_cn.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/** 每日一题 2020.11.28
 * @author ljp
 * @date 2020/11/28 12:02
 *
493. 翻转对
给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

你需要返回给定数组中的重要翻转对的数量。

示例 1:

输入: [1,3,2,3,1]
输出: 2
示例 2:

输入: [2,4,3,5,1]
输出: 3
注意:

给定数组的长度不会超过50000。
输入数组中的所有数字都在32位整数的表示范围内。
 */
public class _困难_493_翻转对 {
    public static void main(String[] args) {
        _困难_493_翻转对 test= new _困难_493_翻转对();
        int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        int ans = test.reversePairs(nums);
        System.out.println(ans);
    }

    /**
     * 官方题解一：归并排序
     * @param nums
     * @return
    执行用时：
    56 ms, 在所有 Java 提交中击败了71.82%的用户
    内存消耗：
    48 MB, 在所有 Java 提交中击败了61.88%的用户
     */
    public int reversePairs(int[] nums) {
        return mergerSort(nums, 0, nums.length - 1);
    }
    private int mergerSort(int[] nums, int left, int right) {
        if(left >= right) {
            return 0;
        }
        int mid = (right + left) >> 1;
        int n1 = mergerSort(nums, left, mid);
        int n2 = mergerSort(nums, mid + 1, right);
        int ret = n1 + n2;
        //统计左边和右边满足条件的个数
        int j = right;
        for(int i = mid; i >= left; --i) {
            while(j > mid && nums[i] <= (long)nums[j] * 2){
                --j;
            }
            ret += j - mid;
        }
        //归并排序
        int[] sorted = new int[right - left + 1];
        int p = 0, l = left, r = mid + 1;
        while(l <= mid || r <= right) {
            if(l <=mid && r <= right){//都没有到结尾
                //拿left和right较小的添加到新数组
                if(nums[l] <= nums[r]){
                    sorted[p] = nums[l++];
                }else{
                    sorted[p] = nums[r++];
                }
            }else{//有一边到结尾了
                if(l <= mid) {
                    sorted[p] = nums[l++];
                }else{
                    sorted[p] = nums[r++];
                }
            }
            ++p;
        }
        for(int i = 0; i < sorted.length; ++i) {
            nums[left + i] = sorted[i];
        }
        return ret;
    }

    /**
     * 官方题解二：树状数组
     * @param nums
     * @return
    执行用时：
    178 ms, 在所有 Java 提交中击败了17.86%的用户
    内存消耗：
    58.4 MB, 在所有 Java 提交中击败了5.09%的用户
     */
    public int reversePairs2(int[] nums) {
        Set<Long> allNumbers = new TreeSet<>();
        for(int x : nums) {
            allNumbers.add((long)x);
            allNumbers.add((long)2 * x);
        }
        Map<Long, Integer> values = new HashMap<>();
        int idx = 0;
        //利用哈希表进行离散化
        for(long x : allNumbers) {
            values.put(x, idx);
            ++idx;
        }
        int ret = 0;
        int size = values.size();
        BIT bit = new BIT(size);
        for(int x : nums) {
            int left = values.get((long) x * 2);
            int right = size - 1;
            ret += bit.query(right + 1) - bit.query(left + 1);
            bit.update(values.get((long)x) + 1, 1);
        }
        return ret;
    }

    /**
     * 树状数组类
     */
    class BIT {
        int[] tree;
        int n;
        public BIT(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }
        public int lowbit(int x){
            return x &  (-x);
        }
        public void update(int x, int d) {
            while(x <= n){
                tree[x] += d;
                x += lowbit(x);
            }
        }
        public int query(int x) {
            int ans = 0;
            while(x > 0) {
                ans += tree[x];
                x -= lowbit(x);
            }
            return ans;
        }
    }
}
