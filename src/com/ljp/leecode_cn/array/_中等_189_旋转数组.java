package com.ljp.leecode_cn.array;

import org.lanqiao.Utils.Util;

/**每日一题 2021.01.08
 * @author lijunpeng
 * @date 2021/1/8 23:53
189. 旋转数组
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。



进阶：

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？


示例 1:

输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]


提示：

1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
0 <= k <= 105
 */
public class _中等_189_旋转数组 {

    public static void main(String[] args) {
        _中等_189_旋转数组 test = new _中等_189_旋转数组();
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        test.rotate3(nums, k);
        Util.print(nums);
    }
    /** 方式一：使用辅助数组
     * 直接赋值（空间复杂度O(n)）
     * @param nums
     * @param k
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了96.54%的用户
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] clone = nums.clone();
        for(int i = 0; i < n; ++i) {
            nums[(i + k) % n] = clone[i];
        }
    }

    /**
     * 循环k次，时间复杂度O(n*k)
     * 空间复杂度O(1)
     * @param nums
     * @param k
    执行用时：
    236 ms, 在所有 Java 提交中击败了24.53%的用户
    内存消耗：
    39.1 MB, 在所有 Java 提交中击败了28.74%的用户
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        while(k > 0) {
            int temp = nums[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                nums[i + 1] = nums[i];
            }
            nums[0] = temp;
            --k;
        }
    }
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int temp = nums[n - 1];
        for(int i = n - 1 - k; i + k != (n - 1 + k) % n; i -= k) {
            if(i < 0) {
                i = i + n;
            }
            nums[(i + k) % n] = nums[i];
        }
        nums[(n - 1 + k) % n] = temp;
    }

}
