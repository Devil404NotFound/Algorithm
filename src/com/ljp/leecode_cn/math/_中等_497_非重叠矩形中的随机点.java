package com.ljp.leecode_cn.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lijunpeng
 * @date 2022/6/9 23:16
 * @description
497. 非重叠矩形中的随机点
给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。

在给定的矩形覆盖的空间内的任何整数点都有可能被返回。

请注意 ，整数点是具有整数坐标的点。

实现 Solution 类:

Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。


示例 1：



输入:
["Solution", "pick", "pick", "pick", "pick", "pick"]
[[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
输出:
[null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]

解释：
Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
solution.pick(); // 返回 [1, -2]
solution.pick(); // 返回 [1, -1]
solution.pick(); // 返回 [-1, -2]
solution.pick(); // 返回 [-2, -2]
solution.pick(); // 返回 [0, 0]


提示：

1 <= rects.length <= 100
rects[i].length == 4
-109 <= ai < xi <= 109
-109 <= bi < yi <= 109
xi - ai <= 2000
yi - bi <= 2000
所有的矩形不重叠。
pick 最多被调用 104 次。
 **/

public class _中等_497_非重叠矩形中的随机点 {
    public static void main(String[] args) {
        int[][] rects = new int[][]{{-2,-2,1,1},{2,2,4,6}};
        Solution solution = new Solution(rects);
        int[] ans = solution.pick();
        System.out.println(ans[0] + "===" + ans[1]);
    }
    private static class Solution {
        int[] preSum;
        int[][] rects;
        public Solution(int[][] rects) {
            // 转化为前缀和
            this.preSum = new int[rects.length + 1];
            for(int i = 0; i < rects.length; i++) {
                int area = (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
                this.preSum[i + 1] = this.preSum[i] + area;
            }
            this.rects = rects;
        }

        public int[] pick() {
            int randomNUm = new Random().nextInt(preSum[preSum.length - 1]);
            int index = getIndex(this.preSum, randomNUm);
            int area = randomNUm - preSum[index];
            int a = this.rects[index][0], b = this.rects[index][1], y = this.rects[index][3];
            int col = y - b + 1;
            int da = area / col;
            int db = area - col * da;
            return new int[]{a + da, b + db};
        }
        private int getIndex(int[] nums ,int target) {
            int ans = 0;
            int left = 0;
            int right = nums.length - 1;
            while(left < right) {
                int mid = left + (right - left) >> 1;
                if(nums[mid] <= target) {
                    left = mid + 1;
                    ans = mid;
                }else {
                    right = mid - 1;
                }
            }
            return ans;
        }
    }
    /**
    * @Author lijunpeng
    * @Date 2022/6/10 0:08
    * @Description
     官方题解一：前缀和+二分查找
    */
    class Solution2 {
        Random rand;
        List<Integer> arr;
        int[][] rects;

        public Solution2(int[][] rects) {
            rand = new Random();
            arr = new ArrayList<Integer>();
            arr.add(0);
            this.rects = rects;
            for (int[] rect : rects) {
                int a = rect[0], b = rect[1], x = rect[2], y = rect[3];
                arr.add(arr.get(arr.size() - 1) + (x - a + 1) * (y - b + 1));
            }
        }

        public int[] pick() {
            int k = rand.nextInt(arr.get(arr.size() - 1));
            int rectIndex = binarySearch(arr, k + 1) - 1;
            k -= arr.get(rectIndex);
            int[] rect = rects[rectIndex];
            int a = rect[0], b = rect[1], y = rect[3];
            int col = y - b + 1;
            int da = k / col;
            int db = k - col * da;
            return new int[]{a + da, b + db};
        }

        private int binarySearch(List<Integer> arr, int target) {
            int low = 0, high = arr.size() - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int num = arr.get(mid);
                if (num == target) {
                    return mid;
                } else if (num > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }
    }
    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(rects);
     * int[] param_1 = obj.pick();
     */
}
