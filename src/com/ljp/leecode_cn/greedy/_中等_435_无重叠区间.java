package com.ljp.leecode_cn.greedy;

import java.util.Arrays;
import java.util.Comparator;

/** 每日一题 2020.12.31
 * 435. 无重叠区间
 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

 注意:

 可以认为区间的终点总是大于它的起点。
 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 示例 1:

 输入: [ [1,2], [2,3], [3,4], [1,3] ]

 输出: 1

 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 示例 2:

 输入: [ [1,2], [1,2], [1,2] ]

 输出: 2

 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 示例 3:

 输入: [ [1,2], [2,3] ]

 输出: 0

 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class _中等_435_无重叠区间 {
    /**
     *
     * @param intervals
     * @return
     * 执行用时 :
    4 ms, 在所有 Java 提交中击败了86.70%的用户
    内存消耗 :
    40 MB, 在所有 Java 提交中击败了8.33%的用户
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0){
            return 0;
        }
        //1.根据区间结尾自定义排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        //2.排除重叠部分
        int min = 0;
        int end = intervals[0][1];
        int i = 1;
        while(i < intervals.length){
            if(intervals[i][0] < end){
                min++;
            }else{
                end = intervals[i][1];
            }
            i++;
        }
        return min;
    }

    /**
     * 2020.12.31重新写一次代码，贪心
     * @param intervals
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了83.29%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了28.65%的用户
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if(intervals.length <= 1) {
            return 0;
        }
        int count = 0;
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }else{
                    return o1[1] - o2[1];
                }
            }
        });
        int start = intervals[0][0];
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; ++i) {
            int[] curr = intervals[i];
            if(curr[0] >= end) {
                start = curr[0];
                end = curr[1];
            }else{
                ++count;
            }
        }
        return count;
    }
}
