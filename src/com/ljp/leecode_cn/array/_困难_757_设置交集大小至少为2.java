package com.ljp.leecode_cn.array;

import java.util.*;

/** 每日一题 2022.07.23
 * @author lijunpeng
 * @date 2022/7/23 14:24
 * @description
757. 设置交集大小至少为2
一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。

给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。

输出这个最小集合S的大小。

示例 1:

输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
输出: 3
解释:
考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
且这是S最小的情况，故我们输出3。
示例 2:

输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
输出: 5
解释:
最小的集合S = {1, 2, 3, 4, 5}.
注意:

intervals 的长度范围为[1, 3000]。
intervals[i] 长度为 2，分别代表左、右边界。
intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 **/

public class _困难_757_设置交集大小至少为2 {
    public static void main(String[] args) {
        //int[][] intervals = new int[][]{{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}};
        //int[][] intervals = new int[][]{{1, 2}, {2, 3}, {2, 4}, {4, 5}};
        int[][] intervals = new int[][]{{1,3},{3,7},{5,7},{7,8}};
        int result = new _困难_757_设置交集大小至少为2().intersectionSizeTwo2(intervals);
        System.out.println(result);
    }
    /** 树状数组：超出内存限制
    * @Author lijunpeng
    * @Date 2022/7/23 15:22
    */
    public int intersectionSizeTwo(int[][] intervals) {
        //升序排序
        Arrays.sort(intervals, (o1, o2) -> (o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]));
        int maxNumber = intervals[intervals.length - 1][1];
        TreeArray treeArray = new TreeArray(maxNumber + 1);
        Set<Integer> hash = new HashSet<>();
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            int sum = treeArray.getSum(right + 1) - treeArray.getSum(left);
            int i = 0;
            while(sum < 2) {
                while(hash.contains(right - i + 1)) {
                    ++i;
                }
                int idx = right - i + 1;
                treeArray.change(idx, 1);
                ++sum;
                hash.add(idx);
            }
        }
        return treeArray.getSum(treeArray.n);
    }
    class TreeArray {
        int[] tree; //下标从1开始
        int n;
        public TreeArray(int n) {
            this.tree = new int[n + 1];
            this.n = n;
        }
        private int lowbit(int i) {
            return i & (-i);
        }
        public void change(int i, int data) {
            while(i <= n) {
                this.tree[i] += data;
                i += lowbit(i);
            }
        }

        public int getSum(int range) {
            int sum = 0;
            while(range > 0) {
                sum += this.tree[range];
                range -= lowbit(range);
            }
            return sum;
        }
    }

    /** 模拟，通过
    * @Author lijunpeng
    * @Date 2022/7/23 15:38
    */
    public int intersectionSizeTwo2(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> (o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]));
        List<Integer> list = new ArrayList<>();
        for(int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            // 如果列表最大的值也比当前区间最小值小，就添加2个
            if(list.isEmpty() || list.get(list.size() - 1) < left) {
                list.add(right - 1);
                list.add(right);
            } else if(list.get(list.size() - 2) < left) { // 如果比第二大的小，就添加1个
                int idx = list.size() - 1;
                // 避免重复
                if(list.get(idx) == right) {
                    list.add(idx, right - 1);
                }else {
                    list.add(right);
                }

            }
        }
        return list.size();
    }
}
