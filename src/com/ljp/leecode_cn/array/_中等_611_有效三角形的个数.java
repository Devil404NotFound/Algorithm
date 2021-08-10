package com.ljp.leecode_cn.array;

import java.util.Arrays;

/** 每日一题 2021.08.04
 * @author lijunpeng
 * @date 2021/8/4 22:43
 * @Description
给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。

示例 1:

输入: [2,2,3,4]
输出: 3
解释:
有效的组合是:
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
注意:

数组长度不超过1000。
数组里整数的范围为 [0, 1000]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-triangle-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _中等_611_有效三角形的个数 {
    /**
     * 官方题解

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/valid-triangle-number/solution/you-xiao-san-jiao-xing-de-ge-shu-by-leet-t2td/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
    执行用时：
    248 ms, 在所有 Java 提交中击败了31.41%的用户
    内存消耗：
    38.2 MB, 在所有 Java 提交中击败了26.44%的用户
     */
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int left = j + 1, right = n - 1, k = j;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                ans += k - j;
            }
        }
        return ans;
    }

    /**
     * 官方题解二：排序 + 双指针
     * @param nums
     * @return
    执行用时：
    39 ms, 在所有 Java 提交中击败了72.39%的用户
    内存消耗：
    38.1 MB, 在所有 Java 提交中击败了50.05%的用户
     */
    public int triangleNumber2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = i;
            for (int j = i + 1; j < n; ++j) {
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    ++k;
                }
                ans += Math.max(k - j, 0);
            }
        }
        return ans;
    }
}
