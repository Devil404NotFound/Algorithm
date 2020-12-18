package com.ljp.leecode_cn.offer;

import org.lanqiao.Utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ljp
 * @date 2020/12/17 19:21
剑指 Offer 57 - II. 和为s的连续正数序列
输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。



示例 1：

输入：target = 9
输出：[[2,3,4],[4,5]]
示例 2：

输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]


限制：

1 <= target <= 10^5
 */
public class _简单_57_和为s的连续正数序列 {
    public static void main(String[] args) {
        _简单_57_和为s的连续正数序列 test = new _简单_57_和为s的连续正数序列();
        int target = 9;
        int[][] ans = test.findContinuousSequence4(target);
        Util.print(ans);
    }

    /**
     * 暴力算法
     * @param target
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了13.62%的用户
    内存消耗：
    38 MB, 在所有 Java 提交中击败了9.19%的用户
     */
    public int[][] findContinuousSequence(int target) {
        int[] nums = new int[(target + 3) >> 1];
        for(int i = 0;  i < nums.length; ++i) {
            nums[i] = i;
        }
        List<int[]> list = new ArrayList<>();
        for(int i = 1; i < nums.length; ++i) {
            int sum = 0;
            for(int j  = i; sum < target; ++j) {
                sum += j;
                if(sum == target) {
                    int[] element = Arrays.copyOfRange(nums, i, j + 1);
                    list.add(element);
                }
            }
        }
        int[][] ans = new int[list.size()][];
        for(int i = 0; i < ans.length; ++i) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 官方题解一：枚举+暴力
     * @param target
     * @return
    执行用时：
    8 ms, 在所有 Java 提交中击败了15.84%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了95.30%的用户
     */
    public int[][] findContinuousSequence2(int target) {
        List<int[]> vec = new ArrayList<>();
        int limit = (target - 1) / 2; //(target - 1) / 2等效于向下取整
        for(int i = 1; i <= limit; ++i) {
            int sum = 0;
            int j = i;
            while(sum < target) {
                sum += j;
                if(sum == target) {
                    int[] res = new int[j - i + 1];
                    for (int k = i; k <= j; k++) {
                        res[k - i] = k;
                    }
                    vec.add(res);
                }
                ++j;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    /**
     * 官方题解二：枚举+数学优化
     从x到y求和等于target也就是等差数列x到y等于target，即 (x+y) * (y - x + 1) / 2 = target;
     也就是 y^2 + y - x^2 + x - 2 * target = 0;
     此时，a = 1，b = 1, c = -x^2 + x - 2 * target
     x从1遍历到limit，现在变成了求y的一元二次方程
     先求delta = b^2 - 4ac >= 0, 在求出y的正根
     需要满足两个条件
     1. delta的开根号需要是整数
     2. 最后的求根公式的分子需要为偶数，因为分母为2
     * @param target
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了63.84%的用户
    内存消耗：
    36.7 MB, 在所有 Java 提交中击败了45.91%的用户
     */
    public int[][] findContinuousSequence3(int target) {
        List<int[]> vec = new ArrayList<>();
        int limit = (target - 1) / 2; //(target - 1) / 2等效于向下取整
        for(int x = 1; x <= limit; ++x) {
            long delta = 1 - 4 * (x - (long)x * x  - 2 * target); //求delta
            if(delta < 0) {//delta小于0无解
                continue;
            }
            int delta_sqrt = (int)Math.sqrt(0.5 + delta); //开根号
            if((long) delta_sqrt * delta_sqrt == delta && (delta_sqrt - 1) % 2 == 0) { //同时满足开根号是整数同时求根公式的分子为偶数
                int y = (-1 + delta_sqrt) >> 1; //求根
                if(x < y) {
                    int[] res = new int[y - x + 1];
                    for(int i = x; i <= y; ++i) {
                        res[i - x] = i;
                    }
                    vec.add(res);
                }
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    /**
     * 双指针
     * @param target
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了94.26%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了94.64%的用户
     */
    public int[][] findContinuousSequence4(int target) {
        List<int[]> list = new ArrayList<>();
        int limit = (target + 1) / 2; //向上取整
        int left = 1, right = 0, sum = 0; //注意细节，left为1,right为1
        while(right <= limit) {
            if(sum > target) {
                sum -= left;
                ++left;
            }else if(sum < target) {
                ++right;
                sum += right;
            }else{
                int[] res = new int[right - left + 1];
                for(int i = left; i <= right; ++i) {
                    res[i - left] = i;
                }
                list.add(res);
                sum -= left;
                ++left;
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
