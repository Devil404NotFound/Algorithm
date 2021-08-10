package com.ljp.leecode_cn.array;

import java.util.*;

/** 每日一题 2021.02.13
 * @author lijunpeng
 * @date 2021/2/13 21:52
448. 找到所有数组中消失的数字
给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:

输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]
 */
public class _简单_448_找到所有数组中消失的数字 {
    /**
     * 利用额外空间Set
     * @param nums
     * @return
    执行用时：
    22 ms, 在所有 Java 提交中击败了17.33%的用户
    内存消耗：
    48.2 MB, 在所有 Java 提交中击败了5.03%的用户
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();//使用额外的Set集合保存1-n
        for(int i = 1; i <= n; ++i) {
            set.add(i);
        }
        //将nums数组出现过的元素都删除
        for(int i = 0; i < n; ++i) {
            set.remove(nums[i]);
        }
        //遍历Set集合
        List<Integer> ans = new ArrayList<>();//保存结果
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()) {
            ans.add(iterator.next());
        }
        return ans;
    }

    /**
     * 不借助额外空间，时间复杂度O(n)
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        //将1-n添加到ans结果集
        for (int i = 1; i <= n; i++) {
            ans.add(i);
        }
        //删除nums中存在的元素
        for (int num : nums) {
            ans.remove(Integer.valueOf(num));
        }
        return ans;
    }
}
